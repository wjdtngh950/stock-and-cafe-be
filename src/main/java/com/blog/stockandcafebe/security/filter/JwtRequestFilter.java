package com.blog.stockandcafebe.security.filter;

import com.blog.stockandcafebe.security.service.JwtUserDetailsService;
import com.blog.stockandcafebe.security.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    
    private static final List<String> EXCLUDE_URL = List.of("/api/v1/members", "/api/v1/authenticate");
    private final JwtUserDetailsService jwtUserDetailService;
    private final JwtTokenUtil jwtTokenUtil;
    
    public JwtRequestFilter(
            @Lazy JwtUserDetailsService jwtUserDetailService,
            @Lazy JwtTokenUtil jwtTokenUtil
    ) {
        this.jwtUserDetailService = jwtUserDetailService;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        
        final String requestTokenHeader = request.getHeader("Authorization");
        
        String username = null;
        String jwtToken = null;
        
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                System.out.println("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                System.out.println("JWT Token has expired");
            }
        } else {
            logger.warn("JWT Token does not begin with Bearer String");
        }
        
        if (username != null && SecurityContextHolder.getContext()
                                                     .getAuthentication() == null) {
            UserDetails userDetails = this.jwtUserDetailService.loadUserByUsername(username);
            
            if (Boolean.TRUE.equals(jwtTokenUtil.validateToken(jwtToken, userDetails))) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext()
                                     .setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
    
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return EXCLUDE_URL.stream()
                          .anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));
    }
    
}