package com.study.server.service;

import org.springframework.http.ResponseEntity;

/**
 * @author: xiaocai
 * @since: 2023/01/15/12:27
 */
public interface UserService {
    public ResponseEntity senMsg(String phone);

    ResponseEntity loginVerification(String mobile, String code);

    //获取当前用户手机号
    String getUserPhone(String userId);

    ResponseEntity updatePhoneVerification(String mobile, String code);
}
