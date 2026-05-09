package com.hxc.springbootweek08.like;

import com.hxc.springbootweek08.like.dto.LikeActionView;
import com.hxc.springbootweek08.like.dto.LikeRequest;
import com.hxc.springbootweek08.like.dto.LikeStatusView;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final StringRedisTemplate stringRedisTemplate;

    @Override
    public LikeActionView like(LikeRequest request) {
        validateRequest(request);

        String key = LikeRedisKey.buildLikeSetKey(request.bizType(), request.bizId());
        Long added = stringRedisTemplate.opsForSet().add(key, request.userId());
        long likeCount = getLikeCount(key);

        if (Long.valueOf(1L).equals(added)) {
            return new LikeActionView(true, "点赞成功", true, likeCount);
        }
        return new LikeActionView(true, "已经点过赞了", true, likeCount);
    }

    @Override
    public LikeActionView unlike(LikeRequest request) {
        validateRequest(request);

        String key = LikeRedisKey.buildLikeSetKey(request.bizType(), request.bizId());
        Long removed = stringRedisTemplate.opsForSet().remove(key, request.userId());
        long likeCount = getLikeCount(key);

        if (Long.valueOf(1L).equals(removed)) {
            return new LikeActionView(true, "取消点赞成功", false, likeCount);
        }
        return new LikeActionView(true, "当前用户尚未点赞", false, likeCount);
    }

    @Override
    public LikeStatusView getStatus(String bizType, String bizId, String userId) {
        validateField("业务类型不能为空", bizType);
        validateField("业务 ID 不能为空", bizId);
        validateField("用户 ID 不能为空", userId);

        String key = LikeRedisKey.buildLikeSetKey(bizType, bizId);
        boolean liked = Boolean.TRUE.equals(stringRedisTemplate.opsForSet().isMember(key, userId));
        long likeCount = getLikeCount(key);
        return new LikeStatusView(bizType, bizId, userId, liked, likeCount);
    }

    private void validateRequest(LikeRequest request) {
        validateField("业务类型不能为空", request.bizType());
        validateField("业务 ID 不能为空", request.bizId());
        validateField("用户 ID 不能为空", request.userId());
    }

    private void validateField(String message, String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(message);
        }
    }

    private long getLikeCount(String key) {
        Long size = stringRedisTemplate.opsForSet().size(key);
        return size == null ? 0L : size;
    }
}
