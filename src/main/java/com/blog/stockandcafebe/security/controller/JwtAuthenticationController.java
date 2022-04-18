package com.blog.stockandcafebe.security.controller;

import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import com.blog.stockandcafebe.security.dto.JwtRequest;
import com.blog.stockandcafebe.security.dto.JwtResponse;
import com.blog.stockandcafebe.security.service.JwtUserDetailsService;
import com.blog.stockandcafebe.security.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/api/v1")
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

