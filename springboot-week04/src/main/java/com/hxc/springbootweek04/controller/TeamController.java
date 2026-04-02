package com.hxc.springbootweek04.controller;

import com.hxc.springbootweek04.common.Result;
import com.hxc.springbootweek04.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/team")
@Slf4j
public class TeamController {

    @PostMapping("/add")
    public Result<String> addTeam(@Validated @RequestBody Team team) {
        log.info("添加团队: {}", team);
        return Result.success("添加成功");
    }
}
