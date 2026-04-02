package com.hxc.springbootweek05;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.hxc.springbootweek05.mapper")
@SpringBootApplication
public class SpringbootWeek05Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeek05Application.class, args);
    }

}
