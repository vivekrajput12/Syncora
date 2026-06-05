package com.collaboration.syncora.webSocket;

import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@AllArgsConstructor
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private HttpHandshakeInterceptor httpHandshakeInterceptor;
        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry){
            registry.addEndpoint("/ws-live")
                    .setAllowedOriginPatterns("*")
                    .addInterceptors(httpHandshakeInterceptor)
                    .withSockJS();
        }
        @Override
        public void configureMessageBroker(MessageBrokerRegistry registry) {
            registry.setApplicationDestinationPrefixes("/app"); // Client → Server
            registry.enableSimpleBroker("/topic", "/queue");     // Server → Client
            registry.setUserDestinationPrefix("/user");          // For convertAndSendToUser()
        }

    @Override
    public void configureClientInboundChannel(ChannelRegistration channelRegistration){
        channelRegistration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor stompHeaderAccessor = MessageHeaderAccessor.getAccessor(message , StompHeaderAccessor.class);
                if(StompCommand.CONNECT.equals(stompHeaderAccessor.getCommand())){
                    String uid = (String) stompHeaderAccessor.getSessionAttributes().get("uid");
                    if(uid!= null){
                        stompHeaderAccessor.setUser(new UsernamePasswordAuthenticationToken(uid , null));
                        System.out.println("🌟 Principal assigned to WebSocket session = " + uid);
                    }
                }
                return message;
            }
        });
    }
}
