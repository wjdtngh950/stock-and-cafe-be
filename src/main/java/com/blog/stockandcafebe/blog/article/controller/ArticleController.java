package com.blog.stockandcafebe.blog.article.controller;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.article.service.ArticleService;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.security.entity.MemberUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;


    @GetMapping("/articles/{articleId}")
    public ResponseEntity<ArticleDto> read(@PathVariable Long articleId) {
        return new ResponseEntity<>(articleService.getDetail(articleId), HttpStatus.OK);
    }

    @GetMapping(value = "/articles", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDto<ArticleDto, Article>> getList(@RequestBody PageRequestDto pageRequestDto) {
        return new ResponseEntity<>(articleService.getPage(pageRequestDto), HttpStatus.OK);
    }

    @PostMapping(value = "/articles")
    public ResponseEntity<ArticleDto> register(
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody ArticleDto articleDto
    ) {
        articleDto.setWriter(memberUser.getMemberDto());
        return new ResponseEntity<>(articleService.register(articleDto), HttpStatus.OK);
    }

    @PatchMapping(value = "articles/{articleId}")
    public ResponseEntity<ArticleDto> modify(
            @PathVariable Long articleId,
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody ArticleDto articleDto
    ) {
        return new ResponseEntity<>(
                articleService.modify(articleId, memberUser.getMemberDto().getEmail(), articleDto),
                HttpStatus.OK
        );
    }


    @DeleteMapping(value = "/articles/{articleId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long articleId,
            @AuthenticationPrincipal MemberUser memberUser
    ) {
        articleService.remove(articleId, memberUser.getMemberDto().getEmail());
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}

