package com.blog.stockandcafebe.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class SACExceptionAdvisor {
    
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(Exception e) {
        log.error("IllegalArgumentException");
        log.error("[" + e.getClass() + "] " + e.getMessage());
        return "error/404";
    }
    
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e) {
        log.error("Exception");
        log.error("[" + e.getClass() + "] " + e.getMessage());
        return "error/404";
    }
    
}
