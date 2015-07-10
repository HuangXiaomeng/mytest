package com.armon.test.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class HelloClient {
	public static void main(String args[]) {
		// server服务启动器
		NioClientSocketChannelFactory channelFactory = 
				new NioClientSocketChannelFactory(
						Executors.newCachedThreadPool(), 
						Executors.newCachedThreadPool());
		ClientBootstrap bootstrap = new ClientBootstrap(channelFactory);
		
		// 设置一个处理客户端消息和各种消息事件的类(Handler)
		ChannelPipelineFactory pipelineFactory = new ChannelPipelineFactory(){
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("handler", new HelloClientHandler());
				return pipeline;
			}
		};
		bootstrap.setPipelineFactory(pipelineFactory);
		
		//连接到本地的8000端口的服务端
		ChannelFuture future = bootstrap.connect(new InetSocketAddress("127.0.0.1", 8000));
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		
		bootstrap.releaseExternalResources();
	}
	
	private static class HelloClientHandler extends SimpleChannelUpstreamHandler {
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
			System.out.println("hello, I am client!");
		}
		
		@Override
		public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) {
			String message = (String)e.getMessage();
			System.out.println(message);
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
			System.out.println("exception: " + e.getCause());
			e.getChannel().close();
		}
	}
}
