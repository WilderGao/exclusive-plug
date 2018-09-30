package com.qg.exclusiveplug.handlers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author WilderGao
 * time 2018-09-30 23:27
 * motto : everything is no in vain
 * description
 */
@Service
@Slf4j
public class MyWebSocketHandler implements WebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
        log.info("成功建立连接");
    }

    @Override
    public void handleMessage(WebSocketSession webSocketSession,
                              WebSocketMessage<?> webSocketMessage) throws Exception {
        log.info("接收信息 >> {}",webSocketMessage.getPayload());

    }

    @Override
    public void handleTransportError(WebSocketSession webSocketSession,
                                     Throwable throwable) throws Exception {
        log.info("{}连接出现异常",webSocketSession.getId());
    }

    @Override
    public void afterConnectionClosed(WebSocketSession webSocketSession,
                                      CloseStatus closeStatus) throws Exception {
        log.info("Socket会话结束，即将移除socket");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
