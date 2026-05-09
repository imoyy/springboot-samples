package com.hxc.springbootweek08.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "点赞请求")
public record LikeRequest(
        @Schema(description = "业务类型，例如 post、comment", example = "post")
        String bizType,
        @Schema(description = "业务 ID", example = "1001")
        String bizId,
        @Schema(description = "用户 ID", example = "u10001")
        String userId
) {
}
