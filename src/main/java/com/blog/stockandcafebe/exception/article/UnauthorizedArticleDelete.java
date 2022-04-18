package com.blog.stockandcafebe.exception.article;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedArticleDelete extends SACException {

    public UnauthorizedArticleDelete() {
        super(ResponseCode.ARTICLE_UNAUTHORIZED_DELETE);
    }

    public UnauthorizedArticleDelete(ResponseCode responseCode) {
        super(responseCode);
    }

}
