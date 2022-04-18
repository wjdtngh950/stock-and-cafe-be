package com.blog.stockandcafebe.exception.member;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedMemberModify extends SACException {

    public UnauthorizedMemberModify() {
        super(ResponseCode.MEMBER_UNAUTHORIZED_MODIFY);
    }

    public UnauthorizedMemberModify(ResponseCode responseCode) {
        super(responseCode);
    }

}
