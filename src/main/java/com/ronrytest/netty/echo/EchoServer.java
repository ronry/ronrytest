package com.ronrytest.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class EchoServer {
	
	private int port;
	
	public EchoServer(int port){
		this.port=port;
	}
	
	public void start() throws Exception{
		EventLoopGroup acceptorGroup=new NioEventLoopGroup();
		EventLoopGroup ioGroup=new NioEventLoopGroup();
		
		ServerBootstrap bootstrap=new ServerBootstrap();
		bootstrap.localAddress(port).group(acceptorGroup, ioGroup).channel(NioServerSocketChannel.class).childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new EchoServerHandler());
			}
		});
		
		try {
			ChannelFuture channelFuture=bootstrap.bind().sync();
			
			channelFuture.channel().closeFuture().sync();
			
		} catch (InterruptedException e) {
			acceptorGroup.shutdownGracefully().sync();
			ioGroup.shutdownGracefully().sync();
		}
		
	}
	
	static class EchoServerHandler extends ChannelInboundHandlerAdapter{
		
	    @Override
	    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf buf = (ByteBuf) msg;
			buf.markReaderIndex();

			byte[] b = new byte[buf.readableBytes()];
			buf.readBytes(b);
			buf.resetReaderIndex();

			String sMsg = new String(b, "UTF-8");

			System.out.println("receive " + sMsg);
	    	ctx.write(msg);
	    }
	    
	    @Override
	    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
			ctx.writeAndFlush(Unpooled.EMPTY_BUFFER);
	    }
	    
	    @Override
	    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
	            throws Exception {
	        ctx.close();
	    }
	}

	public static void main(String[] args) throws Exception {
		EchoServer server = new EchoServer(7903);
		server.start();

		System.out.println("server running.");
	}

}
