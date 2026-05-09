package com.hxc.springbootweek08.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "发送短信验证码响应")
public record SendCodeResponse(
        @Schema(description = "业务提示", example = "验证码发送成功")
        String message,
        @Schema(description = "验证码有效期（秒）", example = "300")
        long expireSeconds
) {
}
