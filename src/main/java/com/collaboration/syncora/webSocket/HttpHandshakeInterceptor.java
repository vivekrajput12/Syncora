package com.collaboration.syncora.webSocket;

import com.collaboration.syncora.security.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.security.Principal;
import java.util.Map;

@Component
@AllArgsConstructor
public class HttpHandshakeInterceptor implements HandshakeInterceptor {
    private JwtUtils jwtUtils;
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                   WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        HttpServletRequest httpServletRequest = ((ServletServerHttpRequest) request).getServletRequest();
        String jwt = jwtUtils.getJwtFromCookies(httpServletRequest);
        System.out.println("socket testing jwt " + jwt);
        if(jwt != null && jwtUtils.validateToken(jwt)){
            String userUid = jwtUtils.getUserUIDFromJwt(jwt);
            if(userUid != null){
                attributes.put("uid",userUid);
            }
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                               WebSocketHandler wsHandler, Exception exception) {

    }
}
