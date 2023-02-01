package com.study.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.study.entiy.UserInfo;

/**
 * 黑名单功能
 */
public interface BlackListApi {

    //分页查询黑名单列表
    IPage<UserInfo> findByUserId(Long userId, int page, int size);

    //取消黑名单用户
    void delete(Long userId, Long blackUserId);
}
