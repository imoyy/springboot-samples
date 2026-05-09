package com.hxc.springbootweek08.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "通用接口响应")
public record ApiResult<T>(
        @Schema(description = "是否成功", example = "true")
        boolean success,
        @Schema(description = "提示信息", example = "操作成功")
        String message,
        @Schema(description = "响应数据")
        T data
) {
    public static <T> ApiResult<T> success(String message, T data) {
        return new ApiResult<>(true, message, data);
    }

    public static <T> ApiResult<T> fail(String message) {
        return new ApiResult<>(false, message, null);
    }
}
