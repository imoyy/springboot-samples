package com.hxc.springbootweek09.config;

import com.hxc.springbootweek09.interceptor.AuthInterceptor;
import com.hxc.springbootweek09.interceptor.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor())
                .addPathPatterns("/api/**")
                .order(1);

        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns("/api/**")
                .order(2);
    }
}
