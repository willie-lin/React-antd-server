package com.ntap.antd.reactservice.controller;

import com.ntap.antd.reactservice.exceptions.AppException;
import com.ntap.antd.reactservice.models.Role;
import com.ntap.antd.reactservice.models.RoleName;
import com.ntap.antd.reactservice.models.User;
import com.ntap.antd.reactservice.payload.ApiResponse;
import com.ntap.antd.reactservice.payload.JwtAuthenticationResponse;
import com.ntap.antd.reactservice.payload.LoginRequest;
import com.ntap.antd.reactservice.payload.SignUpRequest;
import com.ntap.antd.reactservice.repository.RoleRepository;
import com.ntap.antd.reactservice.repository.UserRepository;
import com.ntap.antd.reactservice.security.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * Created with IntelliJ IDEA.
 *
 * @author YuAn
 * @Package com.ntap.antd.reactservice.controller
 * @auther YuAn
 * @Date 2018/11/22
 * @Time 15:19
 * @Project_name React-antd-server
 * To change this template use File | Settings | File Templates.
 * @Description ${DESCRIPTION}
 */

@Api(value = "/api/auth", tags = "用户管理")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Resource
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;


    @ApiOperation(value = "登陆验证", notes = "用户登陆")
    @ApiImplicitParam(name = "loginRequest", value = "LoginRequest", required = true, dataType = "LoginRequest")
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }


    @ApiOperation(value = "注册验证", notes = "用户注册")
    @ApiImplicitParam(name = "signUpRequest", value = "SignUpRequest", required = true, dataType = "SignUpRequest")
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest){

//        判断用户名是否存在
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>(new ApiResponse(false, "Username is already token!"),
                    HttpStatus.BAD_REQUEST);
        }

//        判断邮箱是否存在
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {

            return new ResponseEntity<>(new ApiResponse(false, "Email Address is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }
        // 创建新用户
        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword());
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // 设置用户权限
        Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
                () -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));
        System.out.println(user.getCreatedAt());
        User result = userRepository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(result.getUsername()).toUri();
        return ResponseEntity.created(location).body(
                new ApiResponse(true, "User registered successfully!")
        );
    }


}
