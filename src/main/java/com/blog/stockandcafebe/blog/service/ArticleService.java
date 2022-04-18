package com.blog.stockandcafebe.blog.service;

import com.blog.stockandcafebe.blog.dto.ArticleDto;
import com.blog.stockandcafebe.blog.dto.MemberDto;
import com.blog.stockandcafebe.blog.dto.common.PageRequestDto;
import com.blog.stockandcafebe.blog.dto.common.PageResultDto;
import com.blog.stockandcafebe.blog.entity.Article;

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
    
    ArticleDto getDetail(Long id);
    
    PageResultDto<ArticleDto, Article> getPage(PageRequestDto requestDto);
    
    ArticleDto modify(Long id, MemberDto memberDto, ArticleDto articleDto);
    
    void remove(Long id);
    
}
