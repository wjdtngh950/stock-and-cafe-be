package com.blog.stockandcafebe.blog.controller;

import com.blog.stockandcafebe.blog.dto.ArticleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ArticleController {
    @GetMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ArticleDto>> getList() {
        log.info("[Get] /articles");
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping(value = "/articles")
    public ResponseEntity<Long> register(@RequestBody ArticleDto articleDto) {
        log.info("[Post] /articles " + articleDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
}
