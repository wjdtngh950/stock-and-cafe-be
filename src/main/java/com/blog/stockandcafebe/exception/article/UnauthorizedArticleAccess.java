package com.blog.stockandcafebe.exception.article;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedArticleAccess extends SACException {

    public UnauthorizedArticleAccess() {
        super(ResponseCode.ARTICLE_UNAUTHORIZED_READ);
    }

    public UnauthorizedArticleAccess(ResponseCode responseCode) {
        super(responseCode);
    }

}
