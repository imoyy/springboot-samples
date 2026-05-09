package com.hxc.springbootweek08.sms;

import com.hxc.springbootweek08.sms.dto.ValidateCodeView;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidateCodeView handleBusinessException(BusinessException exception) {
        return new ValidateCodeView(false, exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidateCodeView handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldError() == null
                ? "请求参数不合法"
                : exception.getBindingResult().getFieldError().getDefaultMessage();
        return new ValidateCodeView(false, message);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidateCodeView handleConstraintViolationException(ConstraintViolationException exception) {
        return new ValidateCodeView(false, exception.getMessage());
    }
}
