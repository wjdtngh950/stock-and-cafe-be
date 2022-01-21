package com.blog.stockandcafebe.repository;

import com.blog.stockandcafebe.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
