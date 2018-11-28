package com.ntap.antd.reactservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.ws.rs.ApplicationPath;
import java.time.Instant;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.payload
 * @auther YuAn
 * @Date 2018/11/27
 * @Time 15:23
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Data
@AllArgsConstructor
public class UserProfile {

    private Long id;

    private String name;

    private String username;

    private Instant joinedAt;




}
