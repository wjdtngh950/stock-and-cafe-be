package com.blog.stockandcafebe.exception.reply;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class ReplyNotExist extends SACException {

    public ReplyNotExist() {
        super(ResponseCode.REPLY_NOT_FOUND);
    }

    public ReplyNotExist(ResponseCode responseCode) {
        super(responseCode);
    }

}
