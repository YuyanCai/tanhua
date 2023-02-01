package com.study.api;


import com.study.mongo.Movement;
import com.study.vo.PageResult;

public interface MovementApi {

    //发布动态
    void publish(Movement movement);

    //根据用户id，查询此用户发布的动态数据列表
    PageResult findByUserId(Long userId, Integer page, Integer pagesize);
}
