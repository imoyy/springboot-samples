package com.hxc.springbootweek08.like;

public final class LikeRedisKey {

    private LikeRedisKey() {
    }

    public static String buildLikeSetKey(String bizType, String bizId) {
        return "like:set:" + bizType + ":" + bizId;
    }
}
