package com.hxc.springbootweek04.controller;

import com.hxc.springbootweek04.entity.Student;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bean")
public class BaseController {
    @Resource
    Student student;

    @RequestMapping("/test")
    public Student test() {
        return student;
    }
}