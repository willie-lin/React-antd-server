package com.ntap.antd.reactservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.util.TimeZone;

/**
 * @author YuAn
 */
@EntityScan(basePackageClasses = {
        ReactServiceApplication.class,
        Jsr310JpaConverters.class
})
@SpringBootApplication
public class ReactServiceApplication {

    void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactServiceApplication.class, args);
    }
}
