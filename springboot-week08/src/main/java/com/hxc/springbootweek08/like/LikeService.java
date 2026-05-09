package com.hxc.springbootweek08.like;

import com.hxc.springbootweek08.like.dto.LikeActionView;
import com.hxc.springbootweek08.like.dto.LikeRequest;
import com.hxc.springbootweek08.like.dto.LikeStatusView;

public interface LikeService {

    LikeActionView like(LikeRequest request);

    LikeActionView unlike(LikeRequest request);

    LikeStatusView getStatus(String bizType, String bizId, String userId);
}
