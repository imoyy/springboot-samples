package com.hxc.springbootweek05.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hxc.springbootweek05.entity.User;
import com.hxc.springbootweek05.mapper.UserMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserMpService {

    private final UserMapper userMapper;

    public IPage<User> page(Long current, Long size) {
        Page<User> page = new Page<>(current, size);
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .orderByAsc(User::getId);
        return userMapper.selectPage(page, queryWrapper);
    }

    public List<User> search(String username, String email, Integer minAge, Integer maxAge) {
        return userMapper.search(username, email, minAge, maxAge);
    }

    @Transactional
    public int addTwo(List<User> users, boolean forceRollback) {
        if (users == null || users.size() != 2) {
            throw new IllegalArgumentException("必须传入两个用户");
        }

        int total = 0;
        total += insertOne(users.get(0));

        // Demo switch to verify transactional rollback behavior.
        if (forceRollback) {
            throw new RuntimeException("模拟异常，触发事务回滚");
        }

        total += insertOne(users.get(1));
        return total;
    }

    private int insertOne(User user) {
        user.setId(null);
        user.setCreateTime(LocalDateTime.now());
        return userMapper.insert(user);
    }
}
