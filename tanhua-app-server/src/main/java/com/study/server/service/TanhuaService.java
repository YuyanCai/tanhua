package com.study.server.service;

import cn.hutool.core.collection.CollUtil;
import com.study.api.RecommendUserApi;
import com.study.api.UserInfoApi;
import com.study.dto.RecommendUserDto;
import com.study.entiy.UserInfo;
import com.study.mongo.RecommendUser;
import com.study.server.interceptor.UserHolder;
import com.study.vo.PageResult;
import com.study.vo.TodayBest;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 这里不在用面向接口的方式写了，因为数据库的操作已经使用面向接口的方式写了
 * 这里的service不需要扩展功能，只需要写业务逻辑的处理即可！
 */
@Service
public class TanhuaService {

    @DubboReference
    private RecommendUserApi recommendUserApi;

    @DubboReference
    private UserInfoApi userInfoApi;

    //查询今日佳人数据
    public TodayBest todayBest() {
        //1、获取用户id
        Long userId = UserHolder.getUserId();
        //2、调用API查询
        RecommendUser recommendUser = recommendUserApi.queryWithMaxScore(userId);
        if (recommendUser == null) {
            recommendUser = new RecommendUser();
            recommendUser.setUserId(1l);
            recommendUser.setScore(99d);
        }
        //3、将RecommendUser转化为TodayBest对象
        UserInfo userInfo = userInfoApi.findById(recommendUser.getUserId());
        TodayBest vo = TodayBest.init(userInfo, recommendUser);
        //4、返回
        return vo;
    }

    public PageResult recommendation(RecommendUserDto dto) {
        //1、获取用户id
        Long userId = UserHolder.getUserId();
        //2、调用recommendUserApi分页查询数据列表（PageResult -- RecommendUser）
        PageResult pr = recommendUserApi.queryRecommendUserList(dto.getPage(), dto.getPagesize(), userId);
        //3、获取分页中的RecommendUser数据列表
        List<RecommendUser> items = (List<RecommendUser>) pr.getItems();
        //4、判断列表是否为空
        if (items == null) {
            return pr;
        }
        //5、提取所有推荐的用户id列表
        List<Long> ids = CollUtil.getFieldValues(items, "userId", Long.class);
        //5.1、构建查询条件
        UserInfo userInfo = new UserInfo();
        userInfo.setAge(dto.getAge());
        userInfo.setGender(dto.getGender());
        //6、构建查询条件，批量查询所有的用户详情
        Map<Long, UserInfo> map = userInfoApi.findByIds(ids, userInfo);
        //7、循环推荐的数据列表，构建vo对象
        List<TodayBest> list = new ArrayList<>();
        for (RecommendUser item : items) {
            UserInfo info = map.get(item.getUserId());
            if (info != null) {
                TodayBest vo = TodayBest.init(info, item);
                list.add(vo);
            }
        }
        //8、构造返回值
        pr.setItems(list);
        return pr;
    }
}
