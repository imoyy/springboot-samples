package com.hxc.springboothello.exception;

import com.hxc.springboothello.model.ResultVO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResultVO<Void> handleNotFound(NoResourceFoundException ex) {
        return ResultVO.error(404, "资源不存在: " + ex.getResourcePath());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultVO<Void> handleIllegalArgument(IllegalArgumentException ex) {
        return ResultVO.error(400, "非法请求参数: " + ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResultVO<Void> handleGeneral(Exception ex) {
        return ResultVO.error(500, "服务器内部错误: " + ex.getMessage());
    }
}
