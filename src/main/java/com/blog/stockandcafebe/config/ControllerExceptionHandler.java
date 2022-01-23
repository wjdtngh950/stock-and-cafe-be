package com.blog.stockandcafebe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("Exception");
        log.error("[" + e.getClass() + "] " + e.getMessage());
        return "error/404";
    }

}
