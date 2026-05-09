package com.hxc.springbootweek08.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "校验短信验证码请求")
public record ValidateCodeRequest(
        @NotBlank(message = "手机号不能为空")
        @Schema(description = "手机号", example = "13900003333")
        String phone,
        @NotBlank(message = "验证码不能为空")
        @Schema(description = "验证码", example = "123456")
        String code
) {
}
