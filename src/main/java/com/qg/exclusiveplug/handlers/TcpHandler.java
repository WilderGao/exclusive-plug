package com.qg.exclusiveplug.handlers;

import com.qg.exclusiveplug.enums.StateEnum;
import com.qg.exclusiveplug.exception.ExclusivePlugException;
import com.qg.exclusiveplug.service.TcpService;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.annotation.AccessType;
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
    @Autowired
    private TcpService tcpService;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String s)
            throws Exception {
        log.info("客户端请求，Id为： "+channelHandlerContext.channel().id());
        log.info("收到客户端发来的消息: "+s);
        int result = tcpService.messageHandler(s);
        if (result == StateEnum.OK.getState()){
            log.info("数据成功保存到map中");
        }else {
            log.error("数据出错");
            throw new ExclusivePlugException(StateEnum.ANALYSIS_ERROR);
        }
        channelHandlerContext.channel().flush();
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
    public void channelActive(ChannelHandlerContext ctx){
        log.info("已经连接上了 : "+ctx.channel().remoteAddress());
        ctx.fireChannelActive();
        if (log.isDebugEnabled()){
            log.debug(ctx.channel().remoteAddress()+" ");
        }

    }
}
