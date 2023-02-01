package com.study.server.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.study.api.UserApi;
import com.study.autoconfig.template.SmsTemplate;
import com.study.vo.ErrorResult;
import com.study.entiy.User;
import com.study.server.service.UserService;
import com.study.utils.JwtUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiaocai
 * @since: 2023/01/15/12:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SmsTemplate smsTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @DubboReference
    private UserApi userApi;

    @Override
    public ResponseEntity senMsg(String phone) {
        //1.随机生成6位数字
//        String code = RandomStringUtils.random(6);
//        smsTemplate.sendMessage(phone, code);

        String code = "123456";
        redisTemplate.opsForValue().set("CHECK_CODE_" + phone, code, Duration.ofMinutes(5));//验证码失效时间
        return ResponseEntity.ok("验证码已发送，请注意查收！");
    }

    @Override
    public ResponseEntity loginVerification(String mobile, String code) {
        String redisKey = "CHECK_CODE_";
//       1. 获取redis中存入的验证码
        String value = redisTemplate.opsForValue().get(redisKey + mobile);
//        2.校验
        if (value == null | !value.equals(code)) {
            return ResponseEntity.status(500).body(ErrorResult.loginError());
        }
//        3.登录成功之后删除掉验证码
        redisTemplate.delete(redisKey+mobile);

//        4.根据手机号查询用户
        User user = userApi.findByMobile(mobile);
//        5.如果用户不存在，创建用户对象存入数据库
        boolean isNew = false;
        if (user == null){
            user = new User();
            user.setMobile(mobile);
            user.setPassword(DigestUtil.md5Hex("123456"));
            Long userId = userApi.save(user);
            user.setId(userId);
            isNew = true;
        }
//        6.通过JWT生成token（存入id和手机号码）
        Map tokenMap = new HashMap();
        tokenMap.put("id",user.getId());
        tokenMap.put("mobile",mobile);
        String token = JwtUtils.getToken(tokenMap);

        //7、构造返回值
        Map map = new HashMap();
        map.put("token", token);
        map.put("isNew",isNew);
        return ResponseEntity.ok(map);
    }

    @Override
    public String getUserPhone(String userId) {
        String userPhone = userApi.findUserPhone(userId);
        return userPhone;
    }

//    更换手机号验证码校验
    @Override
    public ResponseEntity updatePhoneVerification(String mobile, String code) {
        String redisKey = "CHECK_CODE_";
//       1. 获取redis中存入的验证码
        String value = redisTemplate.opsForValue().get(redisKey + mobile);
//        2.校验
        if (value == null | !value.equals(code)) {
            return ResponseEntity.status(500).body(ErrorResult.loginError());
        }
//        3.登录成功之后删除掉验证码
        redisTemplate.delete(redisKey+mobile);

        return ResponseEntity.ok(null);
    }
}

