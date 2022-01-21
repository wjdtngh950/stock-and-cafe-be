package com.blog.stockandcafebe.controller;

import com.blog.stockandcafebe.dto.MemberDto;
import com.blog.stockandcafebe.dto.ReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
public class MemberController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MemberDto>> getList() {
        log.info("[Get] /members");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody MemberDto memberDto) {
        log.info("[Post] /members " + memberDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
