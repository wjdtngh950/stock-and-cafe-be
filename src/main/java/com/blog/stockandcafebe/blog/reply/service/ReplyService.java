package com.blog.stockandcafebe.blog.reply.service;

import com.blog.stockandcafebe.blog.article.service.ArticleService;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.service.MemberService;
import com.blog.stockandcafebe.blog.reply.controller.dto.ReplyDto;
import com.blog.stockandcafebe.blog.reply.controller.dto.ReplyResponseDto;
import com.blog.stockandcafebe.blog.reply.repository.entity.Reply;

public interface ReplyService {
    static Reply dtoToEntity(ReplyDto dto) {
        return Reply.builder()
                .replyId(dto.getReplyId())
                .text(dto.getText())
                .writer(MemberService.dtoToEntity(dto.getWriter()))
                .article(ArticleService.dtoToEntity(dto.getArticle()))
                .build();
    }

    static ReplyDto entityToDto(Reply entity) {
        return ReplyDto.builder()
                .replyId(entity.getReplyId())
                .text(entity.getText())
                .writer(MemberService.entityToDto(entity.getWriter()))
                .article(ArticleService.entityToDto(entity.getArticle()))
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    static ReplyResponseDto entityToResponseDto(Reply entity) {
        return ReplyResponseDto.builder()
                .replyId(entity.getReplyId())
                .text(entity.getText())
                .writerEmail(entity.getWriter().getEmail())
                .writerName(entity.getWriter().getName())
                .articleId(entity.getArticle().getArticleId())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    ReplyResponseDto register(Long articleId, String writerEmail, ReplyDto dto);

    PageResultDto<ReplyResponseDto, Reply> getPageByArticleId(Long articleId, PageRequestDto requestDto);

    ReplyResponseDto modify(Long articleId, String writerEmail, Long replyId, ReplyDto replyDto);

    void remove(Long articleId, String writerEmail, Long replyId);

}
