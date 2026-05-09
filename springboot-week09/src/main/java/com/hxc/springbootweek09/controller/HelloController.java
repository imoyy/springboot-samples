package com.hxc.springbootweek09.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, Filter matched!";
    }

    @GetMapping("/other/hello")
    public String other() {
        return "Hello, Filter not matched!";
    }
}
