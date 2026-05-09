package com.hxc.springbootweek08.sms;

public interface SmsSender {

    void send(String phone, String code);
}
