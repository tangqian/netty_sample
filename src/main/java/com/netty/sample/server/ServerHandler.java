/**
 * @Date 2016年6月7日 下午4:07:59
 * @author tangq
 * @version V1.0
 * 
 */
package com.netty.sample.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
 *  
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {


    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println("c->s:" + msg);
    	String pong = "20000{\"msgId\":11,\"timestamp\":1452671865416,\"body\":{}}";
    	ctx.writeAndFlush(pong);
	}



	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
