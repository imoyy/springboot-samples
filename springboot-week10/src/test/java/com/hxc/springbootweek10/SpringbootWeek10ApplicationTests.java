package com.hxc.springbootweek10;

import com.hxc.springbootweek10.service.MailService;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@SpringBootTest
class SpringbootWeek10ApplicationTests {

    @Autowired
    private MailService mailService;

    @Test
    void contextLoads() {
    }

    @Test
    void sendRichMail() throws MessagingException {
        String html = """
                <!DOCTYPE html>
                <html lang="zh-CN">
                <head>
                    <meta charset="UTF-8">
                    <title>注册成功</title>
                </head>
                <body style="font-family: Arial, sans-serif; background-color: #f4f4f4; padding: 20px;">
                    <div style="max-width: 600px; margin: 0 auto; background: #fff; border-radius: 8px; padding: 40px; box-shadow: 0 2px 8px rgba(0,0,0,0.1);">
                        <h1 style="color: #333; text-align: center;">注册成功</h1>
                        <p style="color: #666; font-size: 16px; line-height: 1.6;">恭喜您，您的账号已成功注册！</p>
                        <p style="color: #666; font-size: 14px;">欢迎加入我们，祝您使用愉快！</p>
                        <div style="text-align: center; margin-top: 30px;">
                            <a href="#" style="display: inline-block; padding: 12px 30px; background-color: #007bff; color: #fff; text-decoration: none; border-radius: 4px;">立即体验</a>
                        </div>
                    </div>
                </body>
                </html>
                """;
        mailService.sendRichMail("imoyy@qq.com", "注册成功通知", html);
    }

    @Test
    void sendAttachmentMail() throws MessagingException, IOException {
        File file1 = new File("attachment1.txt");
        File file2 = new File("attachment2.txt");
        Files.writeString(file1.toPath(), "这是第一个附件的内容");
        Files.writeString(file2.toPath(), "这是第二个附件的内容");

        mailService.sendAttachmentMail("imoyy@qq.com", "测试邮件附件", "请查收附件", file1, file2);

        file1.delete();
        file2.delete();
    }

}
