package com.ntap.antd.reactservice.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.payload
 * @auther YuAn
 * @Date 2018/11/22
 * @Time 10:34
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Data
public class LoginRequest {

    /**
     * 登陆请求负载
     */

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

}
