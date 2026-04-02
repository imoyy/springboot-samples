package com.hxc.springbootweek05.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hxc.springbootweek05.common.Result;
import com.hxc.springbootweek05.entity.User;
import com.hxc.springbootweek05.service.UserMpService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user-mp-controller")
@RestController
@RequestMapping("/api/user/mp")
@RequiredArgsConstructor
public class UserMpController {

    private final UserMpService userMpService;

    @Operation(summary = "MyBatis-Plus page demo")
    @GetMapping("/page")
    public Result<IPage<User>> page(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        return Result.success(userMpService.page(current, size));
    }

    @Operation(summary = "MyBatis XML dynamic SQL search demo")
    @GetMapping("/search")
    public Result<List<User>> search(
            @RequestParam(required = false) String username,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Integer minAge,
            @RequestParam(required = false) Integer maxAge) {
        return Result.success(userMpService.search(username, email, minAge, maxAge));
    }

    @Operation(summary = "Transactional add two users demo")
    @PostMapping("/addTwo")
    public Result<String> addTwo(
            @RequestBody List<User> users,
            @RequestParam(defaultValue = "false") boolean forceRollback) {
        int row = userMpService.addTwo(users, forceRollback);
        return Result.success("添加成功，共写入 " + row + " 条记录");
    }
}
