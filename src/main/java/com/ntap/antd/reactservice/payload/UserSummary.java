package com.ntap.antd.reactservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.ntap.antd.reactservice.payload
 * @auther YuAn
 * @Date 2018/11/27
 * @Time 15:19
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */
@Data
@AllArgsConstructor
public class UserSummary {

    private Long id;

    private String username;

    private String name;
}
