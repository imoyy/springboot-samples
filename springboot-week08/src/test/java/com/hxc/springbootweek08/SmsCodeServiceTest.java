package com.hxc.springbootweek08;

import com.hxc.springbootweek08.sms.SmsVerifyCodeService;
import com.hxc.springbootweek08.sms.dto.SendCodeResponse;
import com.hxc.springbootweek08.sms.dto.ValidateCodeView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.LocalDate;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SmsCodeServiceTest {

    @Autowired
    private SmsVerifyCodeService smsVerifyCodeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void shouldSendAndVerifySmsCode() {
        String phone = "13900003333";
        String codeKey = "sms:code:" + phone;
        String cooldownKey = "sms:cooldown:" + phone;
        String today = LocalDate.now(ZoneId.of("Asia/Shanghai")).toString().replace("-", "");
        String dailyCountKey = "sms:count:" + today + ":" + phone;

        stringRedisTemplate.delete(codeKey);
        stringRedisTemplate.delete(cooldownKey);
        stringRedisTemplate.delete(dailyCountKey);

        SendCodeResponse sendResponse = smsVerifyCodeService.sendCode(phone);
        assertEquals("验证码发送成功", sendResponse.message());
        assertEquals(300L, sendResponse.expireSeconds());

        String code = stringRedisTemplate.opsForValue().get(codeKey);
        assertNotNull(code);
        assertEquals(6, code.length());

        ValidateCodeView verifyResponse = smsVerifyCodeService.verifyCode(phone, code);
        assertTrue(verifyResponse.success());
        assertEquals("验证码校验通过", verifyResponse.message());

        ValidateCodeView verifyAgainResponse = smsVerifyCodeService.verifyCode(phone, code);
        assertFalse(verifyAgainResponse.success());
        assertEquals("验证码不存在或已过期", verifyAgainResponse.message());
    }
}
