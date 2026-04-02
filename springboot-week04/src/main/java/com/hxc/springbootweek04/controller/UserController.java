package com.hxc.springbootweek04.controller;

import com.hxc.springbootweek04.common.Result;
import com.hxc.springbootweek04.entity.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RestController
@RequestMapping("/api/user")
public class UserController {
    @RequestMapping("/info")
    public Result<User> getUserInfo() {
        User user = new User();
        user.setId(1L);
        user.setUsername("hxc");
        user.setCreateTime(LocalDateTime.now());
        return Result.success(user);
    }
}