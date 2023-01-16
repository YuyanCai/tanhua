package com.study.api;


import com.study.entiy.UserInfo;

public interface UserInfoApi {

    public void save(UserInfo userInfo);

    public void update(UserInfo userInfo);

    //根据id查询
    UserInfo findById(Long id);
}
