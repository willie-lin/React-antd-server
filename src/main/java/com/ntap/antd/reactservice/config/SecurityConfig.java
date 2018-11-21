package com.ntap.antd.reactservice.config;

import com.ntap.antd.reactservice.security.JwtAuthenticationEntryPoint;
import com.ntap.antd.reactservice.security.JwtAuthenticationFilter;
import com.ntap.antd.reactservice.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.config
 * @auther YuAn
 * @Date 2018/11/21
 * @Time 15:21
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }


    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        super.configure(authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder()));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    /**
     *
     * @param http
     * @throws Exception
     * 构建一个API，用他们的名字，用户名，电子邮件和密码注册新用户。
     *
     * 构建API以允许用户使用其用户名/电子邮件和密码登录。验证用户凭据后，API应生成JWT身份验证令牌并在响应中返回令牌。
     *
     * 客户端将在Authorization所有请求的标头中发送此JWT令牌以访问任何受保护资源。
     *
     * 配置Spring安全性以限制对受保护资源的访问。例如，
     *
     * 每个人都应该可以访问用于登录，注册以及任何静态资源（如图像，脚本和样式表）的API。
     *
     * 用于创建投票，投票到投票等的API应仅供经过身份验证的用户访问。
     *
     * 如果客户端尝试在没有有效JWT令牌的情况下访问受保护资源，则将Spring安全性配置为抛出401未经授权的错误。
     *
     * 配置基于角色的授权以保护服务器上的资源
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //        super.configure(http);
        http.cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.jpeg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()
                .antMatchers("/api/auth/**")
                .permitAll()
                .antMatchers("/api/user/CheckUsernameAvailability",
                        "/api/user/CheckEmailAvailability")
                .permitAll()
                .antMatchers(HttpMethod.GET, "/api/**/**", "/api/**/**")
                .permitAll()
                .anyRequest()
                .authenticated();

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
