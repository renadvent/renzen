package com.ren.renzen.Websockets;

import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

//set up websocket server-side
//work on later

@Component
@EnableWebSocketMessageBroker(1)
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer { (2)

    static final String MESSAGE_PREFIX = "/topic"; (3)

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { (4)
        registry.addEndpoint("/payroll").withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) { (5)
        registry.enableSimpleBroker(MESSAGE_PREFIX);
        registry.setApplicationDestinationPrefixes("/app");
    }
}