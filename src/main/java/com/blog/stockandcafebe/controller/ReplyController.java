package com.blog.stockandcafebe.controller;

import com.blog.stockandcafebe.dto.ReplyDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/replies")
public class ReplyController {
    @GetMapping(value = "/article/{articleId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDto>> getRepliesByArticleId(@PathVariable Long articleId) {
        log.info("[Get] /replies/article/" + articleId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping(value = "/member/{memberId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ReplyDto>> getRepliesByMemberId(@PathVariable String memberId) {
        log.info("[Get] /replies/member/" + memberId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> register(@RequestBody ReplyDto replyDto) {
        log.info("[Post] /replies " + replyDto);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
