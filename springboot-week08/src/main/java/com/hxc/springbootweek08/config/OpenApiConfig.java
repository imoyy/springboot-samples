package com.hxc.springbootweek08.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI smsCodeOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("短信验证码接口文档")
                .description("用于演示短信验证码发送与校验流程的 OpenAPI 文档")
                .version("1.0.0")
                .contact(new Contact().name("Moyy")));
    }
}
