package com.study.api;

import com.study.entiy.UserInfo;
import com.study.mapper.UserInfoMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author: xiaocai
 * @since: 2023/01/16/15:55
 */
@DubboService
public class UserInfoApiImpl implements  UserInfoApi {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void save(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public void update(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public UserInfo findById(Long id) {
        return userInfoMapper.selectById(id);
    }
}
