package com.joecis.quick_fix.jwt;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joecis.quick_fix.role.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret; 
    private static final SignatureAlgorithm algorithm =
            SignatureAlgorithm.HS512;

    private Key getSigningKey() {
        return new SecretKeySpec(
                secret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA512");
    }

    public String generateToken(Long id, Set<Role> roles ) {
        Map<String, Object> claims = new HashMap<String, Object>();
        List<String> roleList = roles.stream()
                                .map(role -> 
                                        role.getAuthority())
                                .collect(Collectors.toList());
        
        claims.put("nameid", id);
        claims.put("roles", roleList);
        String token = Jwts
            .builder()
            .claims(claims)
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + 86400000)) 
            .signWith(getSigningKey())
            .compact();

        return token;
    }
    
    public Claims validateJwtAndExtractClaims(String jwt) 
        throws Exception {
        try {
            return Jwts
                .parser()
                .verifyWith((SecretKey) getSigningKey())
                .build()
                .parseSignedClaims(jwt)
                .getPayload();
                
        } catch (Exception e) {
            throw new Exception("Not able to validate JWT.");
        }
    }

    public Long extractUserId(Claims claims) {
        return claims.get("nameid", Long.class);
    }

    @SuppressWarnings("unchecked")
    public List<String> extractUserRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }

    
}
