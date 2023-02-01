package com.study.api;


import com.study.mongo.RecommendUser;
import com.study.vo.PageResult;

public interface RecommendUserApi {

    RecommendUser queryWithMaxScore(Long toUserId);

    PageResult queryRecommendUserList(Integer page, Integer pagesize, Long toUserId);
}
