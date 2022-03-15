package com.blog.stockandcafebe.controller;

import com.blog.stockandcafebe.dto.AccountDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/members")
public class AccountController {
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AccountDto>> getList() {
        log.info("[Get] /members");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody AccountDto accountDto) {
        log.info("[Post] /members " + accountDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
