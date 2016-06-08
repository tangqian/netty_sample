/**
 * @Date 2016年6月7日 下午3:47:07
 * @author tangq
 * @version V1.0
 * 
 */
package com.netty.sample.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.UnsupportedEncodingException;

/** 
 *  
 */
public class PrefixEncoder extends MessageToByteEncoder<String> {

	@Override
	protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws UnsupportedEncodingException {
		byte[] b = msg.getBytes("utf-8");
		out.writeInt(b.length).writeBytes(b);
	}
}