package com.blog.stockandcafebe.exception.member;

import com.blog.stockandcafebe.common.ResponseCode;
import com.blog.stockandcafebe.exception.SACException;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class MemberNotExist extends SACException {

    public MemberNotExist() {
        super(ResponseCode.MEMBER_NOT_FOUND);
    }

    public MemberNotExist(ResponseCode responseCode) {
        super(responseCode);
    }

}
