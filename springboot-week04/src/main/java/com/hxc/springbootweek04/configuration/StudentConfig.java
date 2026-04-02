package com.hxc.springbootweek04.configuration;

import com.hxc.springbootweek04.entity.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    public Student student() {
        Student student = new Student();
        student.setName("XiaoMing");
        student.setEmail("");
        student.setAge(20);
        return student;
    }
}