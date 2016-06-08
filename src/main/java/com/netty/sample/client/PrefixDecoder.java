/**
 * @Date 2016年6月7日 下午3:46:25
 * @author tangq
 * @version V1.0
 * 
 */
package com.netty.sample.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.UnsupportedEncodingException;
import java.util.List;

/** 
 *  
 */
public class PrefixDecoder extends ByteToMessageDecoder {
	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
		if (in.readableBytes() < 4) {
			return;
		}

		int length = in.getInt(0);

		if (in.readableBytes() >= length + 4) {
			in.skipBytes(4);
			byte[] dst = new byte[length];
			in.readBytes(dst);
			try {
				String receiveMsg = new String(dst, "utf-8");
				out.add(receiveMsg);
			} catch (UnsupportedEncodingException e) {
			}
		}
	}
}
