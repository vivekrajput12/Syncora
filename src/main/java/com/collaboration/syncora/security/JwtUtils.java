package com.collaboration.syncora.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiry}")
    private long expiry;

//    public String getJwtFromHeaders(HttpServletRequest request){
//        String bearerToken = request.getHeader("Authorization");
//        if(bearerToken!=null && bearerToken.startsWith("Bearer ")){
//            return bearerToken;
//        }
//        return null;
//    }
public String getJwtFromCookies(HttpServletRequest request) {
    Cookie[] cookies = request.getCookies();
    if (cookies == null) return null;

    for (Cookie cookie : cookies) {
        if ("authToken".equals(cookie.getName())) {
            return cookie.getValue();
        }
    }
    return null;
}
    public String generateToken(UserDetailImpForJwt userDetail){
        String email = userDetail.getEmail();
        String username = userDetail.getUsername();
        Long uid = userDetail.getUid();
        String publicId = userDetail.getPublicId();
        String roles = userDetail.getAuthorities().stream()
                .map(authority->authority.getAuthority())
                .collect(Collectors.joining(","));
        return Jwts.builder()
                .subject(publicId)
                .claim("uid" ,uid)
                .claim("email",email)
                .claim("username",username)
                .claim("roles",roles)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + expiry))
                .signWith(key())
                .compact();
    }
    public Claims getClaimsFromJwt(String token){
        Claims claims =  Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload();
        return claims;
    }
public String getUserUIDFromJwt(String token){
    return Jwts.parser()
            .verifyWith((SecretKey) key())
            .build().parseSignedClaims(token)
            .getPayload().getSubject();
}

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
    public Boolean validateToken(String authToken){
        try {
            Jwts.parser().verifyWith((SecretKey) key())
                    .build().parseSignedClaims(authToken);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }
}
