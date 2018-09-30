package com.qg.exclusiveplug.config;

import com.qg.exclusiveplug.handlers.WebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * @author WilderGao
 * time 2018-09-30 23:19
 * motto : everything is no in vain
 * description
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private WebSocketHandler handler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(handler, "/ws").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("*");
        registry.addHandler(handler, "/sockjs/message").addInterceptors(new WebSocketInterceptor()).setAllowedOrigins("*").withSockJS();
    }
}
