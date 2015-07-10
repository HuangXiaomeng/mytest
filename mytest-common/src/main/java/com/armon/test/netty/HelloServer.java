package com.armon.test.netty;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.string.StringDecoder;
import org.jboss.netty.handler.codec.string.StringEncoder;

public class HelloServer {
	public static void main(String args[]) {
		// server服务启动器
		NioServerSocketChannelFactory channelFactory = 
				new NioServerSocketChannelFactory(
						Executors.newCachedThreadPool(), 
						Executors.newCachedThreadPool());
		ServerBootstrap bootstrap = new ServerBootstrap(channelFactory);
		
		// 设置一个处理客户端消息和各种消息事件的类(Handler)
		ChannelPipelineFactory pipelineFactory = new ChannelPipelineFactory(){
			@Override
			public ChannelPipeline getPipeline() throws Exception {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("decoder", new StringDecoder());
				pipeline.addLast("encoder", new StringEncoder());
				pipeline.addLast("handler", new HelloServerHandler());
				return pipeline;
			}
		};
		bootstrap.setPipelineFactory(pipelineFactory);
		
		//开放8000端口供客户端访问
		bootstrap.bind(new InetSocketAddress(8000));
	}
	
	private static class HelloServerHandler extends SimpleChannelUpstreamHandler {
		@Override
		public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) {
			System.out.println("hello, I am server!");
			e.getChannel().write("hello, I am server!");
		}
		
		@Override
		public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
			System.out.println("exception: " + e.getCause());
			e.getChannel().close();
		}
	}
}
