package com.blog.stockandcafebe.blog.repository;

import com.blog.stockandcafebe.blog.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article> {
    Optional<Article> findByArticleId(Long articleId);
}
