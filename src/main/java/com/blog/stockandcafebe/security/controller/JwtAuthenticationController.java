package com.blog.stockandcafebe.security.controller;

import com.blog.stockandcafebe.blog.entity.Member;
import com.blog.stockandcafebe.security.config.JwtTokenUtil;
import com.blog.stockandcafebe.security.dto.JwtRequest;
import com.blog.stockandcafebe.security.dto.JwtResponse;
import com.blog.stockandcafebe.security.service.JwtUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {
    
    private final JwtTokenUtil jwtTokenUtil;
    
    private final JwtUserDetailsService userDetailService;
    
    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        final Member member = userDetailService.authenticateByEmailAndPassword
                                                       (authenticationRequest.getEmail(),
                                                        authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(member.getEmail());
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
}

