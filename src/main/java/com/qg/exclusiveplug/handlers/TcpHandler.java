package com.qg.exclusiveplug.handlers;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author WilderGao
 * time 2018-09-20 16:43
 * motto : everything is no in vain
 * description
 */
@Component
@Qualifier("serverHandler")
@ChannelHandler.Sharable
@Slf4j
public class TcpHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s)
            throws Exception {
        log.info("客户端请求，Id为： "+channelHandlerContext.channel().id());
        log.info("收到客户端发来的消息: "+s);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("连接出现了异常 ... ");
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        log.info("断开连接 ... ");
        super.channelInactive(ctx);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.fireChannelActive();
        if (log.isDebugEnabled()){
            log.debug(ctx.channel().remoteAddress()+" ");
        }
        String channelKey = ctx.channel().remoteAddress().toString();

    }
}
