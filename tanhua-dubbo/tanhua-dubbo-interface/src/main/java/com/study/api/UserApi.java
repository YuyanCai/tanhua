package com.study.api;

/**
 * @author: xiaocai
 * @since: 2023/01/15/16:38
 */

import com.study.entiy.User;

/** L
 *
 * 公共接口
 */
public interface UserApi {

    //根据手机号码查询用户
    User findByMobile(String mobile);

    Long save(User user);

    //查询当前用户手机号
    String findUserPhone(String userId);
}
