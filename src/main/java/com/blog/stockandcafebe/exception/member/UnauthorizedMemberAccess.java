package com.blog.stockandcafebe.exception.member;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedMemberAccess extends SACException {

    public UnauthorizedMemberAccess() {
        super(ResponseCode.MEMBER_UNAUTHORIZED_READ);
    }

    public UnauthorizedMemberAccess(ResponseCode responseCode) {
        super(responseCode);
    }
    
}
