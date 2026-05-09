package com.hxc.springbootweek08;

import com.hxc.springbootweek08.like.LikeRedisKey;
import com.hxc.springbootweek08.like.LikeService;
import com.hxc.springbootweek08.like.dto.LikeActionView;
import com.hxc.springbootweek08.like.dto.LikeRequest;
import com.hxc.springbootweek08.like.dto.LikeStatusView;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LikeServiceTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void shouldLikeUnlikeAndQueryStatus() {
        LikeRequest request = new LikeRequest("post", "1001", "u10001");
        String key = LikeRedisKey.buildLikeSetKey(request.bizType(), request.bizId());
        stringRedisTemplate.delete(key);

        LikeActionView firstLike = likeService.like(request);
        assertTrue(firstLike.success());
        assertTrue(firstLike.liked());
        assertEquals(1L, firstLike.likeCount());

        LikeActionView duplicateLike = likeService.like(request);
        assertTrue(duplicateLike.success());
        assertEquals("已经点过赞了", duplicateLike.message());
        assertEquals(1L, duplicateLike.likeCount());

        LikeStatusView likedStatus = likeService.getStatus("post", "1001", "u10001");
        assertTrue(likedStatus.liked());
        assertEquals(1L, likedStatus.likeCount());

        LikeActionView unlikeResult = likeService.unlike(request);
        assertTrue(unlikeResult.success());
        assertFalse(unlikeResult.liked());
        assertEquals(0L, unlikeResult.likeCount());

        LikeStatusView unlikedStatus = likeService.getStatus("post", "1001", "u10001");
        assertFalse(unlikedStatus.liked());
        assertEquals(0L, unlikedStatus.likeCount());
    }
}
