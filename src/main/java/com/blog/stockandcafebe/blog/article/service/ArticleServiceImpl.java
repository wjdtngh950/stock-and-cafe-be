package com.blog.stockandcafebe.blog.article.service;

import com.blog.stockandcafebe.blog.article.controller.dto.ArticleDto;
import com.blog.stockandcafebe.blog.article.repository.ArticleRepository;
import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import com.blog.stockandcafebe.blog.article.repository.entity.QArticle;
import com.blog.stockandcafebe.blog.common.dto.PageRequestDto;
import com.blog.stockandcafebe.blog.common.dto.PageResultDto;
import com.blog.stockandcafebe.blog.member.repository.MemberRepository;
import com.blog.stockandcafebe.blog.member.repository.entity.Member;
import com.blog.stockandcafebe.exception.article.ArticleNotExist;
import com.blog.stockandcafebe.exception.article.UnauthorizedArticleDelete;
import com.blog.stockandcafebe.exception.article.UnauthorizedArticleModify;
import com.blog.stockandcafebe.exception.member.MemberNotExist;
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

    private final MemberRepository memberRepository;

    private final ArticleRepository articleRepository;

    @Override
    public ArticleDto register(String writerEmail, ArticleDto dto) {

        Member member = memberRepository.findByEmail(writerEmail)
                .orElseThrow(MemberNotExist::new);

        Article entity = Article.builder()
                .articleId(dto.getArticleId())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();

        Article result = articleRepository.save(entity);

        return ArticleService.entityToDto(result);

    }

    @Override
    public ArticleDto getDetail(Long articleId) {
        Article result = articleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotExist::new);

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
    public ArticleDto modify(Long articleId, String writerEmail, ArticleDto articleDto) {

        Article result = articleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotExist::new);

        if (!result.getWriter().getEmail().equals(writerEmail)) {
            throw new UnauthorizedArticleModify();
        }

        if (Objects.nonNull(articleDto.getTitle())) {
            result.changeTitle(articleDto.getTitle());
        }

        if (Objects.nonNull(articleDto.getContent())) {
            result.changeContent(articleDto.getContent());
        }

        return ArticleService.entityToDto(result);

    }

    @Override
    public void remove(Long articleId, String writerEmail) {

        Article result = articleRepository.findByArticleId(articleId)
                .orElseThrow(ArticleNotExist::new);

        if (!result.getWriter().getEmail().equals(writerEmail)) {
            throw new UnauthorizedArticleDelete();
        }

        articleRepository.delete(result);

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
