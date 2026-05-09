package com.hxc.springbootweek08;

import com.alibaba.fastjson2.JSON;
import com.hxc.springbootweek08.entity.Address;
import com.hxc.springbootweek08.entity.User;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=" +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration," +
                "org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration"
})
public class RedisServiceTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testRedisTemplate() {
        // 测试字符串操作
        redisTemplate.opsForValue().set("code:13900003333", "0000");
        String code = Objects.requireNonNull(redisTemplate.opsForValue().get("code:13900003333")).toString();
        log.info("13900003333 验证码测试结果：{}", code);
        assertEquals("0000", code);

        // 测试对象操作
        Address address = new Address();
        address.setCity("南京市");
        address.setStreet("栖霞区羊山北路1号");
        address.setZipCode("210000");

        User user = new User();
        user.setName("张三");
        user.setAge(22);
        user.setEmail("zhangsan@qq.com");
        user.setAddress(address);
        redisTemplate.opsForValue().set("user:001", user);

        Object userObj = redisTemplate.opsForValue().get("user:001");
        User user2 = JSON.parseObject(JSON.toJSONString(userObj), User.class);
        log.info("user:001 测试结果：{}", user2);

        assertEquals("张三", user2.getName());
        assertEquals(22, user2.getAge());
        assertEquals("zhangsan@qq.com", user2.getEmail());
        assertEquals("南京市", user2.getAddress().getCity());
        assertEquals("栖霞区羊山北路1号", user2.getAddress().getStreet());
        assertEquals("210000", user2.getAddress().getZipCode());
    }
}
