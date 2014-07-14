package com.ronrytest.nio.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

public class EchoServer {

    /**
     * @param args
     */
    public static void main(String[] args) {

        ServerBootstrap bootstarp = new ServerBootstrap(
                                                        new NioServerSocketChannelFactory(
                                                                                          Executors.newCachedThreadPool(),
                                                                                          Executors.newCachedThreadPool()));
        bootstarp.setPipelineFactory(new ChannelPipelineFactory() {

            @Override
            public ChannelPipeline getPipeline() throws Exception {
                ChannelPipeline pipeline = Channels.pipeline();
                pipeline.addLast("", new SimpleChannelHandler() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
                        ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
                        System.out.println(buffer.toString("UTF-8"));
                    }

                });
                return pipeline;
            }
        });

        bootstarp.setOption("child.tcpNoDelay", true);
        bootstarp.setOption("child.keepAlive", true);

        bootstarp.bind(new InetSocketAddress(8088));
    }
}
