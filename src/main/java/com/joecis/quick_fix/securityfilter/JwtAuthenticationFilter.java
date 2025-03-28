package com.joecis.quick_fix.securityfilter;

import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.joecis.quick_fix.jwt.TokenService;

import io.jsonwebtoken.lang.Collections;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    
    private TokenService tokenService;

    public JwtAuthenticationFilter(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String nameid = tokenService.validateJwt(authorizationHeader.substring(7));
                Authentication authentication = new UsernamePasswordAuthenticationToken(nameid, null, Collections.emptyList());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //filterChain.doFilter(request, response);
            } catch (Exception e) {
                throw new AccessDeniedException(e.getMessage());
            }
        } 
        filterChain.doFilter(request, response);
    }

}
