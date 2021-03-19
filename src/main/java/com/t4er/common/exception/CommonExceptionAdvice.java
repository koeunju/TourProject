package com.t4er.common.exception;

import lombok.extern.log4j.Log4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j
@ControllerAdvice
public class CommonExceptionAdvice {

    @ExceptionHandler(NumberFormatException.class)
    public String commonHandler(Exception ex) {
        log.error("error : " + ex.getMessage());
        return "login/errorAlert";
    }
}
