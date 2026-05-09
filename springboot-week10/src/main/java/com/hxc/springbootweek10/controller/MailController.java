package com.hxc.springbootweek10.controller;

import com.hxc.springbootweek10.service.MailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MailController {

    private final MailService mailService;

    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/mail")
    public String sendMail(@RequestParam String to) {
        mailService.sendSimpleMail(to, "测试邮件", "简单邮件测试");
        return "邮件发送成功";
    }
}
