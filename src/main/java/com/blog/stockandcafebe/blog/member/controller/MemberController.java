package com.blog.stockandcafebe.blog.member.controller;

import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import com.blog.stockandcafebe.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping(value = "/members", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MemberDto> register(@RequestBody MemberDto memberDto) {
        log.info("[Post] /members " + memberDto);
        return new ResponseEntity<>(memberService.register(memberDto), HttpStatus.OK);
    }

}
