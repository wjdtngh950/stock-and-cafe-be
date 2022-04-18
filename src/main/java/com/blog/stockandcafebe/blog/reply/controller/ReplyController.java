package com.blog.stockandcafebe.blog.reply.controller;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import com.blog.stockandcafebe.blog.reply.controller.dto.ReplyDto;
import com.blog.stockandcafebe.blog.reply.repository.entity.Reply;
import com.blog.stockandcafebe.blog.reply.service.ReplyService;
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
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/articles/{articleId}/replies")
    public ResponseEntity<ReplyDto> register(
            @PathVariable Long articleId,
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody ReplyDto replyDto
    ) {
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/articles/{articleId}/replies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDto<ReplyDto, Reply>> getRepliesByArticleId(
            @PathVariable Long articleId,
            @RequestBody PageRequestDto pageRequestDto
    ) {
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/members/{memberId}/replies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDto<ReplyDto, Reply>> getRepliesByMember(
            @RequestBody PageRequestDto pageRequestDto,
            @RequestBody MemberDto memberDto
    ) {
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }

    @PatchMapping(value = "/articles/{articleId}/replies")
    public ResponseEntity<ReplyDto> modify(
            @PathVariable Long articleId,
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody ArticleDto articleDto
    ) {
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }


    @DeleteMapping(value = "/replies/articles/{articleId}/{replyId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long articleId,
            @PathVariable Long replyId,
            @AuthenticationPrincipal MemberUser memberUser
    ) {
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }
}
