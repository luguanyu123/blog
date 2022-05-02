package com.lu.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns(addPathPatterns)
//                .excludePathPatterns(excludePathPatterns);
                .addPathPatterns("/admin/**")  //拦截的路径
                .excludePathPatterns("/admin")  //放行的路径
                .excludePathPatterns("/admin/login");
    }
}
