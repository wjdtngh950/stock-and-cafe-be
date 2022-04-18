package com.blog.stockandcafebe.blog.article.controller.dto;

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
public class ArticleDto {

    private Long articleId;

    private String title;

    private String content;

    private MemberDto writer;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}
