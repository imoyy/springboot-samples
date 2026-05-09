package com.hxc.springbootweek08.sms;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }
}
