package com.blog.stockandcafebe.exception;

import com.blog.stockandcafebe.common.ResponseCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SACException extends RuntimeException {

    private ResponseCode responseCode;

    public SACException() {
        super();
    }

    public SACException(ResponseCode responseCode) {
        super(responseCode.getMessage());
        this.responseCode = responseCode;
    }

}
