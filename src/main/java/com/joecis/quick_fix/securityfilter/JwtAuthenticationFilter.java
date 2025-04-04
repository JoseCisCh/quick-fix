package com.joecis.quick_fix.securityfilter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.joecis.quick_fix.jwt.TokenService;
import com.joecis.quick_fix.role.Role;

import io.jsonwebtoken.Claims;
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
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) 
            throws ServletException, IOException {
        
        String authorizationHeader = request.getHeader("Authorization");
        if(authorizationHeader != null 
           && authorizationHeader.startsWith("Bearer ")) {
            try {
                Claims claims = tokenService.
                        validateJwtAndExtractClaims(
                            authorizationHeader.substring(7));

                Long nameid = tokenService.extractUserId(claims);
                List<Role> roles = tokenService.extractUserRoles(claims)
                                           .stream()
                                           .map(Role::new)
                                           .collect(Collectors.toList());
                
                Authentication authentication = 
                        new UsernamePasswordAuthenticationToken(
                                nameid,
                                null,
                                roles);

                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authentication);
                //filterChain.doFilter(request, response);
            } catch (Exception e) {
                throw new AccessDeniedException(e.getMessage());
            }
        } 
        filterChain.doFilter(request, response);
    }

}
