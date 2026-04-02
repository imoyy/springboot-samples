package com.hxc.springbootweek05.controller;

import com.hxc.springbootweek05.common.Result;
import com.hxc.springbootweek05.entity.User;
import com.hxc.springbootweek05.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "user-controller")
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Add user")
    @PostMapping
    public Result<String> add(@RequestBody User user) {
        int row = userService.add(user);
        if (row != 1) {
            return Result.error("添加失败");
        }
        return Result.success("添加成功");
    }

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public Result<User> get(@PathVariable Long id) {
        return Result.success(userService.getById(id));
    }

    @Operation(summary = "List users")
    @GetMapping("/list")
    public Result<List<User>> list() {
        return Result.success(userService.list());
    }

    @Operation(summary = "Update user")
    @PutMapping
    public Result<String> update(@RequestBody User user) {
        int row = userService.update(user);
        if (row != 1) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("/{id}")
    public Result<String> delete(@PathVariable Long id) {
        int row = userService.delete(id);
        if (row != 1) {
            return Result.error("删除失败");
        }
        return Result.success("删除成功");
    }
}
