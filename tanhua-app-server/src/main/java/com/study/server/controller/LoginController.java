package com.study.server.controller;

import com.study.server.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author: xiaocai
 * @since: 2023/01/15/12:26
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 获取登录验证码
     */
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody Map map){
        String phone = (String) map.get("phone");
        return userService.senMsg(phone);
    }

    /**
     * 验证码校验登录
     */
    @PostMapping("/loginVerification")
    public ResponseEntity loginVerification(@RequestBody Map map) {
        String mobile = (String) map.get("phone");
        String code = (String) map.get("verificationCode");
        return userService.login(mobile,code);
    }

}
