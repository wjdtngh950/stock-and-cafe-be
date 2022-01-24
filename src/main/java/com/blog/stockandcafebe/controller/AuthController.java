package com.blog.stockandcafebe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {
    @GetMapping("/all")
    public void testAll() {
        log.info("testAll..........");
    }

    @GetMapping("/member")
    public void testMember() {
        log.info("testMember..........");
    }

    @GetMapping("/manager")
    public void testManager() {
        log.info("testManager..........");
    }

    @GetMapping("/admin")
    public void testAdmin() {
        log.info("testAdmin..........");
    }
}
