package com.startup.UserManagement.config;

import com.startup.UserManagement.dto.ErrorMessage;
import com.startup.UserManagement.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseBody
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BadRequestException.class})
    public ErrorMessage badRequestException(BadRequestException ex, WebRequest request){
        return new ErrorMessage(ex.getErrorCode(), ex.getMessage(), request.getDescription(false));
    }
}
