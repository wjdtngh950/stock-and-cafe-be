package com.blog.stockandcafebe.exception.reply;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedReplyAccess extends SACException {

    public UnauthorizedReplyAccess() {
        super(ResponseCode.REPLY_UNAUTHORIZED_READ);
    }

    public UnauthorizedReplyAccess(ResponseCode responseCode) {
        super(responseCode);
    }

}
