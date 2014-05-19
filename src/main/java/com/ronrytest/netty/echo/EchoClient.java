package com.ronrytest.netty.echo;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EchoClient {

	private int port;

	private Channel channel;

	public EchoClient(int port) {
		this.port = port;
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();

		Bootstrap bootstrap = new Bootstrap();

		bootstrap.remoteAddress("127.0.0.1", port).group(group)
				.channel(NioSocketChannel.class)
				.handler(new ChannelInitializer<SocketChannel>() {

					@Override
					protected void initChannel(SocketChannel ch)
							throws Exception {
						ch.pipeline().addLast(new EchoClientWriteHandler())
								.addLast(new EchoClientReadHandler());
					}
				});

		ChannelFuture f=bootstrap.connect().sync();
		
		channel = f.channel();

	}
	
	public void send(String msg) {
		channel.write(msg);
	}

	static class EchoClientWriteHandler extends ChannelOutboundHandlerAdapter {

		private UnpooledByteBufAllocator buffAllpcator = new UnpooledByteBufAllocator(
				false);

		@Override
		public void write(ChannelHandlerContext ctx, Object msg,
				ChannelPromise promise) throws Exception {

			if (msg instanceof String) {

				System.out.println("begin to send " + msg);

				ByteBuf buf = buffAllpcator.buffer();
				buf.writeBytes(((String) msg).getBytes("UTF-8"));

				ctx.writeAndFlush(buf);
			}
		}

	}

	static class EchoClientReadHandler extends
			SimpleChannelInboundHandler<ByteBuf> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg)
				throws Exception {
			System.out.println(msg);
		}

	}

	public static void main(String[] args) throws Exception {
		EchoClient client = new EchoClient(7903);
		client.start();

		System.out.println("client started");

		client.send("hello");
	}
}
