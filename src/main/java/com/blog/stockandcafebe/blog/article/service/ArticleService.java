package com.blog.stockandcafebe.blog.article.service;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
import com.blog.stockandcafebe.blog.article.controller.dto.ArticleResponseDto;
import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.service.MemberService;

public interface ArticleService {
    static Article dtoToEntity(ArticleDto dto) {
        return Article.builder()
                .articleId(dto.getArticleId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(MemberService.dtoToEntity(dto.getWriter()))
                .build();
    }

    static ArticleDto entityToDto(Article entity) {
        return ArticleDto.builder()
                .articleId(entity.getArticleId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(MemberService.entityToDto(entity.getWriter()))
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    static ArticleResponseDto entityToResponseDto(Article entity) {
        return ArticleResponseDto.builder()
                .articleId(entity.getArticleId())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writerName(entity.getWriter().getName())
                .createdDate(entity.getCreatedDate())
                .updatedDate(entity.getUpdatedDate())
                .build();
    }

    ArticleDto register(String writerEmail, ArticleDto dto);

    ArticleResponseDto getDetail(Long articleId);

    PageResultDto<ArticleResponseDto, Article> getPage(PageRequestDto requestDto);

    ArticleResponseDto modify(Long articleId, String writerEmail, ArticleDto articleDto);

    void remove(Long articleId, String writerEmail);

}
