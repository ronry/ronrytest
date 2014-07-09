package com.ronrytest.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * <pre>
 *  Server关闭：
 *      1 会先拒绝新连接
 *      2 会等待现有的连接的任务执行完成 （不一定要有写数据的逻辑）
 *      3 如果业务线程和io线程不是同一组，则关闭时需要有自定义的机制保证关闭前业务逻辑都执行完
 * </pre>
 * 
 * @author ronry 2014-6-9 上午11:16:11
 */
public class EchoServer {

    private static volatile boolean stop;
	
	private int port;
	
    private EventLoopGroup          acceptorGroup;

    private EventLoopGroup          ioGroup;

    private NioServerSocketChannel  serverChannel;

	public EchoServer(int port){
		this.port=port;
	}
	
	public void start() throws Exception{
        acceptorGroup = new NioEventLoopGroup(1);
        ioGroup = new NioEventLoopGroup(1);
		
		ServerBootstrap bootstrap=new ServerBootstrap();
		bootstrap.localAddress(port).group(acceptorGroup, ioGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new EchoServerHandler());
			}
		});
		
        bootstrap.childOption(ChannelOption.TCP_NODELAY, true);
        bootstrap.childOption(ChannelOption.SO_KEEPALIVE, true);

		try {
			ChannelFuture channelFuture=bootstrap.bind().sync();
			
            serverChannel = (NioServerSocketChannel) channelFuture.channel();
			
		} catch (InterruptedException e) {
			acceptorGroup.shutdownGracefully().sync();
			ioGroup.shutdownGracefully().sync();
		}
		
	}
	
    public void stop() throws Exception {

        // 关闭serversoceket,完成之后就不会再接收新链接;可以被acceptorGroup.shutdownGracefully()替代
        // serverChannel.close().sync();
        // System.out.println(System.currentTimeMillis() + " server socket channel closed");

        // Thread.sleep(8000);

        // 关闭acceptor线程
        acceptorGroup.shutdownGracefully().sync();
        System.out.println(System.currentTimeMillis() + " acceptor group shutdown");

        // Thread.sleep(8000);

        // 再关闭io线程，会等待现有的请求执行完成
        ioGroup.shutdownGracefully().sync();
        System.out.println(System.currentTimeMillis() + " io group shutdown");

        // 是否应该还有一步骤是关闭socket:好像不用
        System.out.println("echo server stopped ");

    }

	static class EchoServerHandler extends ChannelInboundHandlerAdapter{
		
	    @Override
        public void channelRead(final ChannelHandlerContext ctx, Object msg) throws Exception {
            stop = true;
            final ByteBuf buf = (ByteBuf) msg;
			buf.markReaderIndex();

			byte[] b = new byte[buf.readableBytes()];
			buf.readBytes(b);
			buf.resetReaderIndex();

			String sMsg = new String(b, "UTF-8");

			System.out.println("receive " + sMsg);
			
			Runnable response=new Runnable(){

                @Override
                public void run() {
                    for (int i = 0; i < 50; i++) {
                        System.out.println(System.currentTimeMillis() + " write  " + i);
                        ctx.writeAndFlush(buf.copy());
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                        }
                    }
                }
			    
            };

            response.run();

            // 如果异步执行业务逻辑，则关闭时候需要自己保证业务之前前才关闭
            // new Thread(response).start();
	    }
	    
	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
	    }
	    
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	            throws Exception {
            System.out.println("exceptionCaught  ");
	        ctx.close();
	    }

	}

	public static void main(String[] args) throws Exception {
		EchoServer server = new EchoServer(7903);
		server.start();

        while (true) {
            if (stop) {
                Thread.sleep(1000);
                System.out.println("server will stop...");
                server.stop();
                break;
            }
        }

        System.exit(0);
	}

}
