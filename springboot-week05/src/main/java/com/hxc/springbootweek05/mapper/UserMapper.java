package com.hxc.springbootweek05.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hxc.springbootweek05.entity.User;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User> {

    User selectByPrimaryKey(Long id);

    List<User> selectList();

    int updateByPrimaryKey(User user);

    int deleteByPrimaryKey(Long id);

    List<User> search(@Param("username") String username,
                      @Param("email") String email,
                      @Param("minAge") Integer minAge,
                      @Param("maxAge") Integer maxAge);
}
