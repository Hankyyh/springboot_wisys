package com.nebula.wisys.websocket;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketAppConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${websocket.stomp.endpoint}")
    private String webSocketStompEndpoint;

    @Value("${websocket.allowed.origins}")
    private String webSocketAllowedOrigins;

    @Value("${websocket.msg.broker.names}")
    private String[] webSocketMsgBrokerNames;

    @Value("${websocket.app.dest.prefixes}")
    private String[] webSocketAppDestPrefixes;

    @Override
    public void registerStompEndpoints(final StompEndpointRegistry registry) {
        registry.addEndpoint(webSocketStompEndpoint).setAllowedOrigins(webSocketAllowedOrigins);
        // withSockJS will enforce a secure connection
        // registry.addEndpoint(webSocketStompEndpoint).withSockJS();
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry config) {
        // subscription channel filters
        config.enableSimpleBroker(webSocketMsgBrokerNames);
        // incoming destination prefixes
        config.setApplicationDestinationPrefixes(webSocketAppDestPrefixes);
    }

}
