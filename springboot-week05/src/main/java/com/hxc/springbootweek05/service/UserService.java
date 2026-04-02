package com.hxc.springbootweek05.service;

import com.hxc.springbootweek05.entity.User;
import com.hxc.springbootweek05.mapper.UserMapper;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;

    public int add(User user) {
        user.setCreateTime(LocalDateTime.now());
        return userMapper.insert(user);
    }

    public User getById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public List<User> list() {
        return userMapper.selectList();
    }

    public int update(User user) {
        return userMapper.updateByPrimaryKey(user);
    }

    public int delete(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
