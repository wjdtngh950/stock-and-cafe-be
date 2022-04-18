package com.blog.stockandcafebe.blog.service;

import com.blog.stockandcafebe.blog.dto.ArticleDto;
import com.blog.stockandcafebe.blog.dto.MemberDto;
import com.blog.stockandcafebe.blog.dto.common.PageRequestDto;
import com.blog.stockandcafebe.blog.dto.common.PageResultDto;
import com.blog.stockandcafebe.blog.entity.Article;
import com.blog.stockandcafebe.blog.entity.QArticle;
import com.blog.stockandcafebe.blog.repository.ArticleRepository;
import com.blog.stockandcafebe.exception.SACException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Transactional
public class ArticleServiceImpl implements ArticleService {
    
    private final ArticleRepository articleRepository;
    
    @Override
    public ArticleDto register(ArticleDto dto) {
        
        Article entity = ArticleService.dtoToEntity(dto);
        
        Article result = articleRepository.save(entity);
        
        return ArticleService.entityToDto(result);
    }
    
    @Override
    public ArticleDto getDetail(Long id) {
        // TODO: Add exception message. Article doesn't exist
        Article result = articleRepository.findByArticleId(id)
                                          .orElseThrow(() -> new SACException());
        
        return ArticleService.entityToDto(result);
    }
    
    @Override
    public PageResultDto<ArticleDto, Article> getPage(PageRequestDto requestDto) {
        
        Pageable pageable = requestDto.getPagable(Sort.by("articleId")
                                                      .descending());
        
        BooleanBuilder booleanBuilder = getSearch(requestDto);
        
        Page<Article> result = articleRepository.findAll(booleanBuilder, pageable);
        
        Function<Article, ArticleDto> fn = ArticleService::entityToDto;
        
        return new PageResultDto<>(result, fn);
        
    }
    
    @Override
    public ArticleDto modify(Long id, MemberDto memberDto, ArticleDto articleDto) {
        // TODO: Add exception message. Article doesn't exist
        Article article = articleRepository.findByArticleId(id)
                                           .orElseThrow(() -> new SACException());
        
        // TODO: Add exception message. Only the author can modify
        if (!article.getWriter()
                    .getEmail()
                    .equals(memberDto.getEmail())) {
            throw new SACException();
        }
        
        if (Objects.nonNull(articleDto.getTitle())) {
            article.changeTitle(articleDto.getTitle());
        }
        
        if (Objects.nonNull(articleDto.getContent())) {
            article.changeContent(articleDto.getContent());
        }
        
        return ArticleService.entityToDto(article);
    }
    
    @Override
    public void remove(Long id) {
        // TODO: Add exception message. Article doesn't exist
        Article result = articleRepository.findByArticleId(id)
                                          .orElseThrow(() -> new SACException());
        articleRepository.deleteById(id);
    }
    
    private BooleanBuilder getSearch(PageRequestDto requestDto) {
        
        String type = requestDto.getType();
        
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        
        QArticle article = QArticle.article;
        
        String keyword = requestDto.getKeyword();
        
        BooleanExpression expression = article.articleId.gt(0L);
        
        booleanBuilder.and(expression);
        
        if (type == null || type.trim()
                                .length() == 0) {
            return booleanBuilder;
        }
        
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        
        if (type.contains("t")) {
            conditionBuilder.or(article.title.contains(keyword));
        }
        if (type.contains("c")) {
            conditionBuilder.or(article.content.contains(keyword));
        }
        if (type.contains("w")) {
            conditionBuilder.or(article.writer.name.contains(keyword));
        }
        
        booleanBuilder.and(conditionBuilder);
        
        return booleanBuilder;
        
    }
    
}
