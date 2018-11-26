package com.ntap.antd.reactservice.payload;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.payload
 * @auther YuAn
 * @Date 2018/11/22
 * @Time 11:00
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Data
public class JwtAuthenticationResponse {

    /**
     * 响应有效负载
     */

    private String accessToken;

    private String tokenType = "Bearer";

    /**
     * 采用依赖注入的方式
     * @param accessToken
     */

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}
