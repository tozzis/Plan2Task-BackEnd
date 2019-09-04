package com.senior.plan2task.FileService.Filter;

import static com.senior.plan2task.FileService.Filter.GlobalValue.EXPIRATION_TIME;
import static com.senior.plan2task.FileService.Filter.GlobalValue.secretKey;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import static java.util.Collections.emptyList;

@Service
public class TokenAuthenticationService {
    
    public String createToken(HttpServletRequest request) {
        String data = request.getHeader("Authorization");
        
        Date now = new Date();
        Claims claims = Jwts.claims().setSubject(data);

        String token = Jwts.builder()
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
        .signWith(SignatureAlgorithm.HS384,secretKey.getBytes())
        .compact();
        return token;
    }
     
    public String getDataByToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        
        String user = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return user;
    }
    
    public String getDataById(String Id) {
        String token = Id;
        
        String user = Jwts.parser()
                .setSigningKey(secretKey.getBytes())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return user;
    }

    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey(secretKey.getBytes())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }
    
}