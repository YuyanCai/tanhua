package com.study.api;


import com.study.entiy.UserInfo;

import java.util.List;
import java.util.Map;

public interface UserInfoApi {

    public void save(UserInfo userInfo);

    public void update(UserInfo userInfo);

    //根据id查询
    UserInfo findById(Long id);

    Map<Long, UserInfo> findByIds(List<Long> ids, UserInfo userInfo);
}
