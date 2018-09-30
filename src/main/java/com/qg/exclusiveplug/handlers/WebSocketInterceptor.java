package com.qg.exclusiveplug.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;

/**
 * @author WilderGao
 * time 2018-09-30 23:23
 * motto : everything is no in vain
 * description
 */
@Service
@Slf4j
public class WebSocketInterceptor implements HandshakeInterceptor {
    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        log.info("webSocket握手请求...");
        if (serverHttpRequest instanceof ServletServerHttpRequest){

        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
        log.info("webSocket握手结束...");
    }
}
