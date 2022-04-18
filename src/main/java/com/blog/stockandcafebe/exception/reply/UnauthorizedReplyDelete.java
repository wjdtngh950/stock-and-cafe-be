package com.blog.stockandcafebe.exception.reply;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedReplyDelete extends SACException {

    public UnauthorizedReplyDelete() {
        super(ResponseCode.REPLY_UNAUTHORIZED_DELETE);
    }

    public UnauthorizedReplyDelete(ResponseCode responseCode) {
        super(responseCode);
    }

}
