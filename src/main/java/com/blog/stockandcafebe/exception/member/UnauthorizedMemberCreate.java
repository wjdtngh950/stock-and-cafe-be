package com.blog.stockandcafebe.exception.member;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedMemberCreate extends SACException {

    public UnauthorizedMemberCreate() {
        super(ResponseCode.MEMBER_UNAUTHORIZED_CREATE);
    }

    public UnauthorizedMemberCreate(ResponseCode responseCode) {
        super(responseCode);
    }

}
