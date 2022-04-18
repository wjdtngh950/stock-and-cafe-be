package com.blog.stockandcafebe.exception.reply;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedReplyCreate extends SACException {

    public UnauthorizedReplyCreate() {
        super(ResponseCode.REPLY_UNAUTHORIZED_CREATE);
    }

    public UnauthorizedReplyCreate(ResponseCode responseCode) {
        super(responseCode);
    }

}
