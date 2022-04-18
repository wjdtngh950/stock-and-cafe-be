package com.blog.stockandcafebe.blog.article.repository;

import com.blog.stockandcafebe.blog.article.repository.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article> {
    Optional<Article> findByArticleId(Long articleId);

}
