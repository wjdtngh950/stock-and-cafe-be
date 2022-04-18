package com.blog.stockandcafebe.exception.reply;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedReplyModify extends SACException {

    public UnauthorizedReplyModify() {
        super(ResponseCode.REPLY_UNAUTHORIZED_MODIFY);
    }

    public UnauthorizedReplyModify(ResponseCode responseCode) {
        super(responseCode);
    }

}
