package com.project.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS跨域配置
 */
//@Configuration
//public class CorsConfig implements WebMvcConfigurer {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        //设置允许跨域的路径
//        registry.addMapping("/**")
//                //是否允许证书,不再默认开启
//                .allowCredentials(true)
//                .allowedOrigins("*")
//                //设置允许的方法
//                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")
//                //跨域允许时间
//                .maxAge(3600);
//    }
//}