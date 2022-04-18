package com.blog.stockandcafebe.exception.member;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class UnauthorizedMemberDelete extends SACException {

    public UnauthorizedMemberDelete() {
        super(ResponseCode.MEMBER_UNAUTHORIZED_DELETE);
    }

    public UnauthorizedMemberDelete(ResponseCode responseCode) {
        super(responseCode);
    }

}
