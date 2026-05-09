package com.hxc.springbootweek08.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "点赞状态结果")
public record LikeStatusView(
        @Schema(description = "业务类型", example = "post")
        String bizType,
        @Schema(description = "业务 ID", example = "1001")
        String bizId,
        @Schema(description = "用户 ID", example = "u10001")
        String userId,
        @Schema(description = "当前用户是否已点赞", example = "true")
        boolean liked,
        @Schema(description = "当前点赞总数", example = "5")
        long likeCount
) {
}
