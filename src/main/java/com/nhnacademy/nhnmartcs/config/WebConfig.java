package com.nhnacademy.nhnmartcs.config;

import com.nhnacademy.nhnmartcs.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) // 로그인 검증 인터셉터 등록
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }
}
