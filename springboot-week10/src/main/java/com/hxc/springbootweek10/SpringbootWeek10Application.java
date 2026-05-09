package com.hxc.springbootweek10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootWeek10Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWeek10Application.class, args);
    }

}
