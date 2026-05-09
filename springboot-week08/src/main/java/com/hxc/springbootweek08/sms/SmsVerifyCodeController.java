package com.hxc.springbootweek08.sms;

import com.hxc.springbootweek08.sms.dto.SendCodeRequest;
import com.hxc.springbootweek08.sms.dto.SendCodeResponse;
import com.hxc.springbootweek08.sms.dto.ValidateCodeRequest;
import com.hxc.springbootweek08.sms.dto.ValidateCodeView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/sms")
@Tag(name = "短信验证码", description = "短信验证码发送与校验接口")
public class SmsVerifyCodeController {

    private final SmsVerifyCodeService smsVerifyCodeService;

    @PostMapping("/send")
    @Operation(summary = "发送短信验证码")
    public SendCodeResponse sendCode(@Valid @RequestBody SendCodeRequest request) {
        return smsVerifyCodeService.sendCode(request.phone());
    }

    @PostMapping("/verify")
    @Operation(summary = "校验短信验证码")
    public ValidateCodeView verifyCode(@Valid @RequestBody ValidateCodeRequest request) {
        return smsVerifyCodeService.verifyCode(request.phone(), request.code());
    }
}
