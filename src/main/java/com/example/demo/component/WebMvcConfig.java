package com.example.demo.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    MsgBoardInterceptor msgBoardInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(msgBoardInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register")
                .excludePathPatterns("/pages/login.html", "/pages/register.html", "/**/*.css", "/**/*.js");
    }
}
