package com.startup.UserManagement.config;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.function.Function;

@Service
public class JwtTokenUtil {

    @Value("${spring.app.security.jwtSecret}")
    private String jwtSecret;

    public String generateAccessToken(String subject){
        return Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS512,jwtSecret)
                .compact();
    }

    public String getUsername(String token){
        return getClaim(token,Claims::getSubject);
    }

    private <T> T getClaim(String token, Function<Claims,T> claim){
        Claims claims=getAllClaims(token);
        return claim.apply(claims);
    }

    private Claims getAllClaims(String token){
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }

    public boolean validate(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
