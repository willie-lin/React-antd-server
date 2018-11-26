package com.ntap.antd.reactservice.config;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;


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
public class SwaggerConfig   {



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
//                .securitySchemes(Arrays.asList(securityScheme()))
//                .securityContexts(Arrays.asList(securityContext()));

    }

//    @Bean
//    public SecurityConfiguration security() {
//        return SecurityConfigurationBuilder.builder()
//                .clientId(CLIENT_ID)
//                .clientSecret(CLIENT_SECRET)
//                .scopeSeparator(" ")
//                .useBasicAuthenticationWithAccessCodeGrant(true)
//                .build();
//    }
//
//    private SecurityScheme securityScheme() {
//        GrantType grantType = new AuthorizationCodeGrantBuilder()
//                .tokenEndpoint(new TokenEndpoint(AUTH_SERVER + "/token", "oauthtoken"))
//                .tokenRequestEndpoint(
//                        new TokenRequestEndpoint(AUTH_SERVER + "/authorize", CLIENT_ID, CLIENT_ID))
//                .build();
//
//        SecurityScheme oauth = new OAuthBuilder().name("spring_oauth")
//                .grantTypes(Arrays.asList(grantType))
//                .scopes(Arrays.asList(scopes()))
//                .build();
//        return oauth;
//    }
//
//    private AuthorizationScope[] scopes() {
//        AuthorizationScope[] scopes = {
//                new AuthorizationScope("read", "for read operations"),
//                new AuthorizationScope("write", "for write operations"),
//                new AuthorizationScope("foo", "Access foo API") };
//        return scopes;
//    }
//
//    private SecurityContext securityContext() {
//        return SecurityContext.builder()
//                .securityReferences(Arrays.asList(new SecurityReference("spring_oauth", scopes())))
//                .forPaths(PathSelectors.regex("/foos.*"))
//                .build();
//    }





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
