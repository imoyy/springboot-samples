package com.hxc.springbootweek08.sms.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "校验短信验证码响应")
public record ValidateCodeView(
        @Schema(description = "是否校验通过", example = "true")
        boolean success,
        @Schema(description = "业务提示", example = "验证码校验通过")
        String message
) {
}
