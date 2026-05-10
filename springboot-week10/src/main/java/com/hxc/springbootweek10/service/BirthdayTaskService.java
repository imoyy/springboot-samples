package com.hxc.springbootweek10.service;

import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
public class BirthdayTaskService {

    private final MailService mailService;

    public BirthdayTaskService(MailService mailService) {
        this.mailService = mailService;
    }

    private static final DateTimeFormatter MD_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");

    private static final List<Map<String, String>> USERS = List.of(
            Map.of("name", "张三", "email", "imoyy@qq.com", "birthday", "05-09"),
            Map.of("name", "李四", "email", "imoyy@qq.com", "birthday", "05-09")
    );

    @Scheduled(cron = "0 * * * * ?")
    public void sendBirthdayCards() throws MessagingException {
        MonthDay today = MonthDay.from(LocalDate.now());

        for (Map<String, String> user : USERS) {
            MonthDay birthday = MonthDay.parse(user.get("birthday"), MD_FORMATTER);
            if (today.equals(birthday)) {
                String html = """
                        <!DOCTYPE html>
                        <html lang="zh-CN">
                        <head>
                            <meta charset="UTF-8">
                            <title>生日祝福</title>
                        </head>
                        <body style="font-family: Arial, sans-serif; background: linear-gradient(135deg, #ffecd2, #fcb69f); padding: 40px;">
                            <div style="max-width: 500px; margin: 0 auto; background: #fff; border-radius: 16px; padding: 40px; text-align: center; box-shadow: 0 4px 20px rgba(0,0,0,0.1);">
                                <h1 style="color: #e74c3c;">🎂 生日快乐！</h1>
                                <p style="font-size: 18px; color: #555;">亲爱的 <strong style="color: #2c3e50;">%s</strong>，</p>
                                <p style="font-size: 16px; color: #777;">愿您在新的一岁里，健康快乐，万事如意！</p>
                                <p style="font-size: 14px; color: #aaa;">—— 来自团队的美好祝福</p>
                            </div>
                        </body>
                        </html>
                        """.formatted(user.get("name"));
                mailService.sendRichMail(user.get("email"), "🎂 生日快乐！", html);
            }
        }
    }
}
