package com.joecis.quick_fix.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
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
        
        claims.put("nameid", accountUserDto.getUsername());
        claims.put("roles", accountUserDto.getAuthorities());
        String token = Jwts
            .builder()
            .claims(claims)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 86400000)) 
            .signWith(getSigningKey())
            .compact();

        return token;
    }
    
    public String validateJwt(String jwt) throws Exception {
        try {
            return Jwts
                .parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload()
                .get("nameid", String.class);
        } catch (Exception e) {
            throw new Exception("Not able to validate JWT.");
        }
    }
}
