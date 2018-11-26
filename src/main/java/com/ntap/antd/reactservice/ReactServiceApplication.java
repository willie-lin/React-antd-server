package com.ntap.antd.reactservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author YuAn
 */
//@EnableSwagger2
@EntityScan(basePackageClasses = {
        ReactServiceApplication.class,
        Jsr310JpaConverters.class
})
@SpringBootApplication
public class ReactServiceApplication {

    @PostConstruct
    void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactServiceApplication.class, args);
    }
}
