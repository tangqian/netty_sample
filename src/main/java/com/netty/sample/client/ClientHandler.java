/**
 * @Date 2016年6月7日 下午4:07:59
 * @author tangq
 * @version V1.0
 * 
 */
package com.netty.sample.client;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/** 
 *  
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
    	String ping = "10000{\"msgId\":11,\"timestamp\":1452671865416,\"body\":{}}";
        ctx.writeAndFlush(ping);
    }
    
    

    @Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    	System.out.println("s->c:" + msg);
	}



	@Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
