package com.hamzabekkaoui.cvcrudapp.exception;

import com.hamzabekkaoui.cvcrudapp.dto.response.ResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseException responseNotFoundException(ResourceNotFoundException ex){
        return ResponseException.builder()
                .message(ex.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .code(HttpStatus.BAD_REQUEST.value())
                .build();
    }

}
