package com.study.api;

/**
 * @author: xiaocai
 * @since: 2023/01/15/16:39
 */

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.study.entiy.User;
import com.study.mapper.UserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import javax.annotation.Resource;

/**
 * dubbo服务
 */
@DubboService
public class UserApiImpl implements UserApi {

    @Resource
    private UserMapper userMapper;

    //根据手机号码查询用户
    public User findByMobile(String mobile) {
        QueryWrapper<User> qw = new QueryWrapper<>();
        qw.eq("mobile",mobile);
        return userMapper.selectOne(qw);
    }

    @Override
    public Long save(User user) {
        userMapper.insert(user);
        return user.getId();
    }
}
