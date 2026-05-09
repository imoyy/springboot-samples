package com.hxc.springbootweek08;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(properties = {
        "spring.autoconfigure.exclude=" +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration," +
                "org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration," +
                "org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration"
})
class StringRedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void shouldOperateStringValue() {
        String key = "week08:test:string";

        stringRedisTemplate.delete(key);
        stringRedisTemplate.opsForValue().set(key, "hello redis", 5, TimeUnit.MINUTES);

        String value = stringRedisTemplate.opsForValue().get(key);
        Boolean hasKey = stringRedisTemplate.hasKey(key);

        assertEquals("hello redis", value);
        assertTrue(Boolean.TRUE.equals(hasKey));
    }

    @Test
    void shouldIncrementCounter() {
        String key = "week08:test:counter";

        stringRedisTemplate.delete(key);
        stringRedisTemplate.opsForValue().set(key, "0");

        Long value = stringRedisTemplate.opsForValue().increment(key);

        assertEquals(1L, value);
        assertEquals("1", stringRedisTemplate.opsForValue().get(key));
    }

    @Test
    void shouldDeleteKey() {
        String key = "week08:test:delete";

        stringRedisTemplate.opsForValue().set(key, "to-be-deleted");
        Boolean deleted = stringRedisTemplate.delete(key);

        assertTrue(Boolean.TRUE.equals(deleted));
        assertFalse(Boolean.TRUE.equals(stringRedisTemplate.hasKey(key)));
        assertNull(stringRedisTemplate.opsForValue().get(key));
    }
}
