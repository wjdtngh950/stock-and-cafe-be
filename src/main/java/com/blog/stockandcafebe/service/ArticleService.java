package com.blog.stockandcafebe.service;

import com.blog.stockandcafebe.dto.ArticleDto;
import com.blog.stockandcafebe.entity.Article;

public interface ArticleService {
    default Article dtoToEntity(ArticleDto dto) {
        // TODO: implement method
        return null;
    }

    default ArticleDto entityToDto(Article entity) {
        // TODO: implement method
        return null;
    }
}
