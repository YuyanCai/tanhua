package com.study.server.controller;

import com.study.entiy.UserInfo;
import com.study.server.interceptor.UserHolder;
import com.study.server.service.UserInfoService;
import com.study.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersControler {

    @Autowired
    private UserInfoService userInfoService;

    /**
     * 查询用户资料
     *    参数：token
     */
    @GetMapping
    public ResponseEntity users(Long userID) {
        if(userID == null) {
            userID = UserHolder.getUserId();
        }
        UserInfoVo userInfo = userInfoService.findById(userID);
        return ResponseEntity.ok(userInfo);
    }

    /**
     * 更新用户资料
     */
    @PutMapping
    public ResponseEntity updateUserInfo(@RequestBody UserInfo userInfo) {
        userInfo.setId(UserHolder.getUserId());
        userInfoService.update(userInfo);
        return ResponseEntity.ok(null);
    }
}
