/**
 * @Date 2016年6月7日 下午3:22:59
 * @author tangq
 * @version V1.0
 * 
 */
package com.netty.sample.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/** 
 *  
 */
public class SampleClient {

	private static final String HOST = "127.0.0.1";

	private static final int PORT = 110;

	public static void main(String[] args) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new PrefixDecoder());
					ch.pipeline().addLast(new PrefixEncoder());
					ch.pipeline().addLast(new ClientHandler());
				}
			});

			ChannelFuture f = b.connect(HOST, PORT).sync();
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		} finally {
			workerGroup.shutdownGracefully();
		}
	}
}