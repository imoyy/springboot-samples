package com.hxc.springbootweek08.like;

import com.hxc.springbootweek08.like.dto.ApiResult;
import com.hxc.springbootweek08.like.dto.LikeActionView;
import com.hxc.springbootweek08.like.dto.LikeRequest;
import com.hxc.springbootweek08.like.dto.LikeStatusView;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/likes")
@Tag(name = "点赞功能", description = "基于 Redis 的点赞、取消点赞和点赞状态查询接口")
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @Operation(summary = "点赞")
    public ApiResult<LikeActionView> like(@RequestBody LikeRequest request) {
        return ApiResult.success("操作成功", likeService.like(request));
    }

    @PostMapping("/cancel")
    @Operation(summary = "取消点赞")
    public ApiResult<LikeActionView> unlike(@RequestBody LikeRequest request) {
        return ApiResult.success("操作成功", likeService.unlike(request));
    }

    @GetMapping("/status")
    @Operation(summary = "查询点赞状态")
    public ApiResult<LikeStatusView> status(
            @Parameter(description = "业务类型", example = "post")
            @RequestParam String bizType,
            @Parameter(description = "业务 ID", example = "1001")
            @RequestParam String bizId,
            @Parameter(description = "用户 ID", example = "u10001")
            @RequestParam String userId) {
        return ApiResult.success("查询成功", likeService.getStatus(bizType, bizId, userId));
    }
}
