package com.hxc.springbootweek08.sms;

import com.hxc.springbootweek08.sms.dto.SendCodeResponse;
import com.hxc.springbootweek08.sms.dto.ValidateCodeView;

public interface SmsVerifyCodeService {

    SendCodeResponse sendCode(String phone);

    ValidateCodeView verifyCode(String phone, String code);
}
