package com.blog.stockandcafebe.exception.article;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedArticleModify extends SACException {

    public UnauthorizedArticleModify() {
        super(ResponseCode.ARTICLE_UNAUTHORIZED_MODIFY);
    }

    public UnauthorizedArticleModify(ResponseCode responseCode) {
        super(responseCode);
    }

}
