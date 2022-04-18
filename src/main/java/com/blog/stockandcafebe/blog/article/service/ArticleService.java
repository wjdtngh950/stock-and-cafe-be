package com.blog.stockandcafebe.blog.article.service;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
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

    ArticleDto register(ArticleDto dto);

    ArticleDto getDetail(Long articleId);

    PageResultDto<ArticleDto, Article> getPage(PageRequestDto requestDto);

    ArticleDto modify(Long articleId, String writerEmail, ArticleDto articleDto);

    void remove(Long articleId, String writerEmail);

}
