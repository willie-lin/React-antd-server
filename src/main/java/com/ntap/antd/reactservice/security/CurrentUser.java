package com.ntap.antd.reactservice.security;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.ntap.antd.reactservice.security
 * @auther YuAn
 * @Date 2018/11/21
 * @Time 20:02
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Target({ElementType.PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@AuthenticationPrincipal
public @interface CurrentUser {
}
