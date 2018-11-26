package com.ntap.antd.reactservice.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.config
 * @auther YuAn
 * @Date 2018/11/23
 * @Time 14:13
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ntap.antd.reactservice.controller"))
//                .paths(paths())
                .paths(PathSelectors.any())
                .build();

    }

//    private Predicate<String> paths() {
//
//        return Predicates.and(
//                PathSelectors.regex("/.*"),
//                Predicates.not(PathSelectors.regex("/error.*"))
//        );
//    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                .title("Swagger Sample api")
                .description("This page lists all the rest apis for Swagger Sample App.")
//                .termsOfServiceUrl("127.0.0.1:5000/")
                .version("1.0-SNAPSHOT")
                .build();
    }
}
