package com.ipseg.studyTime.framework.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
//    @Bean
//    public FilterRegistrationBean filterRegistrationBean(){
//        // 생성자에 빈값으로 생성하면 안됨 notNull 에러남
//        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
//        filterRegistrationBean.addUrlPatterns("/*");
//        filterRegistrationBean.setOrder(1);
//
//        return filterRegistrationBean;
//    }
}
