package com.collaboration.syncora.security;

import com.collaboration.syncora.dtos.AppPrincipal;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailSerivceImp userDetailSerivceImp;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwt = jwtUtils.getJwtFromCookies(request);
            System.out.println("jwt " + jwt);
            if(jwt!=null && jwtUtils.validateToken(jwt)){
            // if data not found in redis then we go for db hit :
                String uid = jwtUtils.getUserUIDFromJwt(jwt);
                Claims claims = jwtUtils.getClaimsFromJwt(jwt);
                String email = claims.get("email" , String.class);
                System.out.println("phase 1 ");
                UserDetialImpForJwt userDetails = userDetailSerivceImp.loadUserByUsername(email);
                System.out.println("phase 2 "  + userDetails.getAuthorities());
                List<String> roles = userDetails.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)   // convert to string
                        .toList();
                AppPrincipal principal = AppPrincipal.builder()
                        .uid(userDetails.getUid())
                        .email(userDetails.getEmail())
                        .roles(roles)
                        .build();
//                        (userDetails.getUid(), userDetails.getEmail(), roles);
                System.out.println("principal in " + principal);
                if(userDetails != null){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(principal , null , userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        filterChain.doFilter(request,response);
    }
}
