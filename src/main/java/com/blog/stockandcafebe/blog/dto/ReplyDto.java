package com.blog.stockandcafebe.blog.dto;

import com.blog.stockandcafebe.blog.entity.Article;
import com.blog.stockandcafebe.blog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
public class ReplyDto {

    private Long replyId;

    private String text;

    private MemberDto writer;

    private ArticleDto article;

}
