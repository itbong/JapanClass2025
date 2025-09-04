package com.example.sjw.SJWSpring.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenInterceptor())
                .addPathPatterns("/**") //모든 url 에 대해서 이 인터셉터를 통과 시키겠다.
                .excludePathPatterns("/addUser"); //예외시킬 패턴
    }

}
