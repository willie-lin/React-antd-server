package com.ntap.antd.reactservice.controller;

import com.ntap.antd.reactservice.exceptions.ResourceNotFoundException;
import com.ntap.antd.reactservice.models.User;
import com.ntap.antd.reactservice.payload.UserIdentityAvailability;
import com.ntap.antd.reactservice.payload.UserProfile;
import com.ntap.antd.reactservice.payload.UserSummary;
import com.ntap.antd.reactservice.repository.UserRepository;
import com.ntap.antd.reactservice.security.CurrentUser;
import com.ntap.antd.reactservice.security.UserPrincipal;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.ws.rs.GET;

/**
 * Created with IntelliJ IDEA.
 *
 * @Package com.ntap.antd.reactservice.controller
 * @auther YuAn
 * @Date 2018/11/27
 * @Time 15:26
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Api(value = "/api", tags = "用户验证")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(value = "已登录用户", notes = "根据已登录用户获取详情页")
    @ApiImplicitParam(name = "currentUser", value = "已登录用户", required = true, dataType = "Object")
    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummary getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        UserSummary userSummary = new UserSummary(currentUser.getId(), currentUser.getUsername(), currentUser.getName());
        return  userSummary;
    }

    @ApiOperation(value = "验证用户名", notes = "验证用户名是否符合规则")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/user/checkUsernameAvailability")
    public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }

    @ApiOperation(value = "验证游箱", notes = "验证邮箱是否符合格式")
    @ApiImplicitParam(name = "email", value = "用户邮箱", required = true, dataType = "String")
    @GetMapping("/user/checkEmailAvailability")
    public UserIdentityAvailability checkEmailAvailability(@RequestParam(value = "email") String email) {
        Boolean isAvailable = !userRepository.existsByEmail(email);
        return new UserIdentityAvailability(isAvailable);
    }

    @ApiOperation(value = "username", notes = "个人详情页")
    @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String")
    @GetMapping("/user/{username}")
    public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User", "Username", username));

        UserProfile userProfile = new UserProfile(user.getId(), user.getName(), user.getUsername(), user.getCreatedAt());
        return userProfile;
    }

}
