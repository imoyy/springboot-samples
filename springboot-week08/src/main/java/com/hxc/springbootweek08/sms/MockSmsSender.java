package com.hxc.springbootweek08.sms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MockSmsSender implements SmsSender {

    @Override
    public void send(String phone, String code) {
        // 本地开发阶段先模拟短信发送，后续可替换为真实短信供应商实现。
        log.info("模拟发送短信验证码，手机号：{}，验证码：{}", phone, code);
    }
}
