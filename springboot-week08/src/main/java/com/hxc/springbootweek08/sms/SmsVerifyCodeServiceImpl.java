package com.hxc.springbootweek08.sms;

import com.hxc.springbootweek08.sms.dto.SendCodeResponse;
import com.hxc.springbootweek08.sms.dto.ValidateCodeView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class SmsVerifyCodeServiceImpl implements SmsVerifyCodeService {

    private static final Duration CODE_EXPIRE_DURATION = Duration.ofMinutes(5);
    private static final Duration SEND_COOLDOWN_DURATION = Duration.ofSeconds(60);
    private static final int DAILY_SEND_LIMIT = 10;
    private static final String PHONE_REGEX = "^1\\d{10}$";

    private final StringRedisTemplate stringRedisTemplate;
    private final SmsSender smsSender;

    @Override
    public SendCodeResponse sendCode(String phone) {
        validatePhone(phone);

        String cooldownKey = SmsRedisKey.SMS_COOLDOWN_PREFIX + phone;
        if (Boolean.TRUE.equals(stringRedisTemplate.hasKey(cooldownKey))) {
            throw new BusinessException("发送过于频繁，请 60 秒后再试");
        }

        String dailyCountKey = buildDailyCountKey(phone);
        Long sendCount = stringRedisTemplate.opsForValue().increment(dailyCountKey);
        if (sendCount == null) {
            throw new BusinessException("短信发送失败，请稍后重试");
        }
        if (sendCount == 1L) {
            stringRedisTemplate.expire(dailyCountKey, Duration.ofDays(1));
        }
        if (sendCount > DAILY_SEND_LIMIT) {
            throw new BusinessException("今日发送次数已达上限，请明天再试");
        }

        String code = generateCode();
        smsSender.send(phone, code);

        stringRedisTemplate.opsForValue().set(SmsRedisKey.SMS_CODE_PREFIX + phone, code, CODE_EXPIRE_DURATION);
        stringRedisTemplate.opsForValue().set(cooldownKey, "1", SEND_COOLDOWN_DURATION);

        return new SendCodeResponse("验证码发送成功", CODE_EXPIRE_DURATION.toSeconds());
    }

    @Override
    public ValidateCodeView verifyCode(String phone, String code) {
        validatePhone(phone);
        if (code == null || code.isBlank()) {
            throw new BusinessException("验证码不能为空");
        }

        String codeKey = SmsRedisKey.SMS_CODE_PREFIX + phone;
        String cachedCode = stringRedisTemplate.opsForValue().get(codeKey);

        if (cachedCode == null) {
            return new ValidateCodeView(false, "验证码不存在或已过期");
        }
        if (!cachedCode.equals(code)) {
            return new ValidateCodeView(false, "验证码错误");
        }

        stringRedisTemplate.delete(codeKey);
        return new ValidateCodeView(true, "验证码校验通过");
    }

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            throw new BusinessException("手机号格式不正确");
        }
    }

    private String generateCode() {
        return String.format("%06d", ThreadLocalRandom.current().nextInt(1_000_000));
    }

    private String buildDailyCountKey(String phone) {
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Shanghai"));
        return SmsRedisKey.SMS_DAILY_COUNT_PREFIX + today.toString().replace("-", "") + ":" + phone;
    }
}
