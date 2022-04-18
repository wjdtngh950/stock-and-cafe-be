package com.blog.stockandcafebe.exception.article;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ArticleNotExist extends SACException {

    public ArticleNotExist() {
        super(ResponseCode.ARTICLE_NOT_FOUND);
    }

    public ArticleNotExist(ResponseCode responseCode) {
        super(responseCode);
    }

}
