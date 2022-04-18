package com.blog.stockandcafebe.exception.article;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedArticleCreate extends SACException {

    public UnauthorizedArticleCreate() {
        super(ResponseCode.ARTICLE_UNAUTHORIZED_CREATE);
    }

    public UnauthorizedArticleCreate(ResponseCode responseCode) {
        super(responseCode);
    }

}
