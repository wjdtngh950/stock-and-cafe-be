package com.blog.stockandcafebe.blog.reply.controller.dto;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
import com.blog.stockandcafebe.blog.member.controller.dto.MemberDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReplyDto {

    private Long replyId;

    private String text;

    private MemberDto writer;

    private ArticleDto article;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
