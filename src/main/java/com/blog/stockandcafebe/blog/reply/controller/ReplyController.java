package com.blog.stockandcafebe.blog.reply.controller;

import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.reply.controller.dto.ReplyDto;
import com.blog.stockandcafebe.blog.reply.controller.dto.ReplyResponseDto;
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
@CrossOrigin("*")
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ReplyController {

    private final ReplyService replyService;

    @PostMapping(value = "/articles/{articleId}/replies")
    public ResponseEntity<ReplyResponseDto> register(
            @PathVariable Long articleId,
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody ReplyDto replyDto
    ) {
        return new ResponseEntity<>(
                replyService.register(articleId, memberUser.getMemberDto().getEmail(), replyDto),
                HttpStatus.OK
        );
    }

    @GetMapping(value = "/articles/{articleId}/replies", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PageResultDto<ReplyResponseDto, Reply>> getRepliesByArticleId(
            @PathVariable Long articleId,
            @ModelAttribute PageRequestDto pageRequestDto
    ) {
        return new ResponseEntity<>(
                replyService.getPageByArticleId(articleId, pageRequestDto),
                HttpStatus.OK
        );
    }

    @PatchMapping(value = "/articles/{articleId}/replies/{replyId}")
    public ResponseEntity<ReplyResponseDto> modify(
            @PathVariable Long articleId,
            @PathVariable Long replyId,
            @AuthenticationPrincipal MemberUser memberUser,
            @RequestBody ReplyDto replyDto
    ) {
        return new ResponseEntity<>(
                replyService.modify(articleId, memberUser.getMemberDto().getEmail(), replyId, replyDto),
                HttpStatus.OK
        );
    }

    @DeleteMapping(value = "/articles/{articleId}/replies/{replyId}")
    public ResponseEntity<Void> remove(
            @PathVariable Long articleId,
            @PathVariable Long replyId,
            @AuthenticationPrincipal MemberUser memberUser
    ) {
        replyService.remove(articleId, memberUser.getMemberDto().getEmail(), replyId);
        return new ResponseEntity<>(
                null,
                HttpStatus.OK
        );
    }
}
