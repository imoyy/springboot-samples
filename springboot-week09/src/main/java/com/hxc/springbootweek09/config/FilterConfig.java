package com.hxc.springbootweek09.config;

import com.hxc.springbootweek09.filter.AuthFilter;
import com.hxc.springbootweek09.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    // 暂时注释过滤器，专注于拦截器练习
    // @Bean
    // public FilterRegistrationBean<LogFilter> logFilter() {
    //     FilterRegistrationBean<LogFilter> bean = new FilterRegistrationBean<>();
    //     bean.setFilter(new LogFilter());
    //     bean.addUrlPatterns("/api/*");
    //     bean.setOrder(1);
    //     return bean;
    // }
    //
    // @Bean
    // public FilterRegistrationBean<AuthFilter> authFilter() {
    //     FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
    //     bean.setFilter(new AuthFilter());
    //     bean.addUrlPatterns("/api/*");
    //     bean.setOrder(2);
    //     return bean;
    // }
}
