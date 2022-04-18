package com.blog.stockandcafebe.blog.controller;

import com.blog.stockandcafebe.blog.dto.ReplyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReplyController {
    @GetMapping(value = "/replies/articles/{articleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDto>> getRepliesByArticleId(@PathVariable Long articleId) {
        log.info("[Get] /replies/articles/" + articleId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @GetMapping(value = "/replies/members/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDto>> getRepliesByMemberId(@PathVariable String memberId) {
        log.info("[Get] /replies/members/" + memberId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
    
    @PostMapping(value = "/replies")
    public ResponseEntity<Long> register(@RequestBody ReplyDto replyDto) {
        log.info("[Post] /replies " + replyDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
