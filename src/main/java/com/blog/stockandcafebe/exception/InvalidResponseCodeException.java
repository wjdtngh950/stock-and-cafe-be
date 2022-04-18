package com.blog.stockandcafebe.exception;

import com.blog.stockandcafebe.common.ResponseCode;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class InvalidResponseCodeException extends SACException {

    public InvalidResponseCodeException() {
        super(ResponseCode.RESPONSE_CODE_NOT_FOUND);
    }

    public InvalidResponseCodeException(ResponseCode responseCode) {
        super(responseCode);
    }

}
