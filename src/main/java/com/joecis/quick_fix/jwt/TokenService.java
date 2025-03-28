package com.joecis.quick_fix.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joecis.quick_fix.DTO.AccountUserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret; 
    private static final SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;

    private Key getSigningKey() {
        return new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA512");
    }
    public String generateToken(AccountUserDto accountUserDto) {
        Map<String, Object> claims = new HashMap<String, Object>();
        
        accountUserDto.getAuthorities().forEach(role -> System.out.println(role));
        claims.put("nameid", accountUserDto.getUsername());
        claims.put("roles", accountUserDto.getAuthorities());
        String token = Jwts
            .builder()
            .setClaims(claims)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) 
            .signWith(getSigningKey(), algorithm)
            .compact();

        return token;
    }
}
