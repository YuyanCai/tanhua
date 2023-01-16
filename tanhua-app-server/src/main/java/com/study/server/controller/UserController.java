package com.study.server.controller;

import com.study.entiy.UserInfo;
import com.study.server.interceptor.UserHolder;
import com.study.server.service.UserInfoService;
import com.study.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 保存用户信息
     *   UserInfo
     *   请求头中携带token
     */
    @PostMapping("/loginReginfo")
    public ResponseEntity loginReginfo(@RequestBody UserInfo userInfo) {
//        1.判断token是否合法
//        不在controller里做，统一在拦截器做

//        2.向userInfo中设置用户id
        userInfo.setId(UserHolder.getUserId());

//        3.调用service保存用户
        userInfoService.save(userInfo);

        return ResponseEntity.ok(null);
    }

    /**
     * 上传用户头像
     */
    @PostMapping("/loginReginfo/head")
    public ResponseEntity head(MultipartFile headPhoto) throws IOException {
        //        1.判断token是否合法
//        不在controller里做，统一在拦截器做

        userInfoService.updateHead(headPhoto,UserHolder.getUserId());

        return ResponseEntity.ok(null);
    }
}
