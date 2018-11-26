package com.ntap.antd.reactservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.config
 * @auther YuAn
 * @Date 2018/11/22
 * @Time 17:07
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final long MAX_AGE_SECS = 3600;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("HEAD", "OPTIONS", "GET", "PUT", "POST", "PATCH", "DELETE")
                .maxAge(MAX_AGE_SECS);
    }
}
