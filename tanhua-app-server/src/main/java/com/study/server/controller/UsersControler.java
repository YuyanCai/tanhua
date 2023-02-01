package com.study.server.controller;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.study.entiy.UserInfo;
import com.study.server.interceptor.UserHolder;
import com.study.server.service.UserInfoService;
import com.study.server.service.UserService;
import com.study.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UsersControler {

    @Autowired
    private UserInfoService userInfoService;

    @Resource
    private UserService userService;

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

    /**
     * 更新头像
     */
    @PostMapping("/head")
    public ResponseEntity head(MultipartFile headPhoto) throws IOException {
        //        1.判断token是否合法
//        不在controller里做，统一在拦截器做

        userInfoService.updateHead(headPhoto,UserHolder.getUserId());

        return ResponseEntity.ok(null);
    }

    /**
     * 修改手机号码
     *  1.发送短信验证码
     *  2.验证
     *  3.保存
     */
    //发送短信验证码
    @PostMapping("/phone/sendVerificationCode")
    public ResponseEntity sendVerificationCode(){
//        String phone = (String) map.get("phone");
        Long userId = UserHolder.getUserId();
        String userPhone = userService.getUserPhone(String.valueOf(userId));
        return userService.senMsg(userPhone);
    }

    //验证
    @PostMapping("/phone/checkVerificationCode")
    public ResponseEntity checkVerificationCode(@RequestBody Map map){
        String code = (String) map.get("verificationCode");
        Long userId = UserHolder.getUserId();
        String mobile = userService.getUserPhone(String.valueOf(userId));
        return userService.updatePhoneVerification(mobile,code);
    }

    //保存
    @PostMapping("/phone")
    public ResponseEntity savePhone(@RequestBody Map map){
        String mobile = (String) map.get("phone");
        String code = (String) map.get("verificationCode");
        return userService.loginVerification(mobile,code);
    }



}
