package com.qg.exclusiveplug.handlers;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author WilderGao
 * time 2018-09-23 10:00
 * motto : everything is no in vain
 * description 字符串协议
 */
@Component
@Qualifier("springProtocolInitializer")
@Data
public class StringProtocolHandler extends ChannelInitializer<SocketChannel> {
    @Autowired
    private StringEncoder stringEncoder;
    @Autowired
    private StringDecoder stringDecoder;
    @Autowired
    private TcpHandler tcpHandler;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(stringDecoder);
        pipeline.addLast(stringEncoder);
        pipeline.addLast(tcpHandler);
    }
}
