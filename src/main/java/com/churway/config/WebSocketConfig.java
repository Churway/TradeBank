package com.churway.config;


import com.churway.ws.WsHandler;
import com.churway.ws.WsInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * 功能描述:<br>
 * 〈〉
 *
 * @author EDZ
 * @create 2020/10/29
 * @since 1.0.0
 */
//--can on Start.class
@Configuration
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    private WsHandler wsHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(wsHandler,"websocket").addInterceptors(new WsInterceptor()).setAllowedOrigins("*");
    }
}
