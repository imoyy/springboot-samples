package com.hxc.springbootweek08.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "点赞动作结果")
public record LikeActionView(
        @Schema(description = "当前动作是否成功", example = "true")
        boolean success,
        @Schema(description = "业务提示", example = "点赞成功")
        String message,
        @Schema(description = "当前用户是否处于已点赞状态", example = "true")
        boolean liked,
        @Schema(description = "当前点赞总数", example = "3")
        long likeCount
) {
}
