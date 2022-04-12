package com.blog.stockandcafebe.blog.dto;

import com.blog.stockandcafebe.blog.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Builder
@AllArgsConstructor
@Data
public class ArticleDto {

    private Long articleId;

    private String title;

    private String content;

    private MemberDto writer;

}
