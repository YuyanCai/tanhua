package com.study.api;

/**
 * @author: xiaocai
 * @since: 2023/01/15/16:38
 */

import com.study.entiy.User;

/**
 * 公共接口
 */
public interface UserApi {

    //根据手机号码查询用户
    User findByMobile(String mobile);

    Long save(User user);
}
