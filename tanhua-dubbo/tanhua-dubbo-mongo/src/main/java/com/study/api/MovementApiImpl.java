package com.study.api;

import com.study.mongo.Friend;
import com.study.mongo.Movement;
import com.study.mongo.MovementTimeLine;
import com.study.utils.IdWorker;
import com.study.utils.TimeLineService;
import com.study.vo.PageResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@DubboService
public class MovementApiImpl implements MovementApi {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private TimeLineService timeLineService;

    //发布动态
    public void publish(Movement movement) {
        //1、保存动态详情
        try {
            //设置PID
            movement.setPid(idWorker.getNextId("movement"));
            //设置时间
            movement.setCreated(System.currentTimeMillis());
            //movement.setId(ObjectId.get());
            mongoTemplate.save(movement);
            //2、查询当前用户的好友数据
//            Criteria criteria = Criteria.where("userId").is(movement.getUserId());
//            Query query = Query.query(criteria);
//            List<Friend> friends = mongoTemplate.find(query, Friend.class);
//            Thread.sleep(10000);
//            //3、循环好友数据，构建时间线数据存入数据库
//            for (Friend friend : friends) {
//                MovementTimeLine timeLine = new MovementTimeLine();
//                timeLine.setMovementId(movement.getId());
//                timeLine.setUserId(friend.getUserId());
//                timeLine.setFriendId(friend.getFriendId());
//                timeLine.setCreated(System.currentTimeMillis());
//                mongoTemplate.save(timeLine);
//            }
            timeLineService.saveTimeLine(movement.getUserId(),movement.getId());
        } catch (Exception e) {
            //忽略事务处理
            e.printStackTrace();
        }
    }

    //@Override
    public PageResult findByUserId(Long userId, Integer page, Integer pagesize) {
        Criteria criteria = Criteria.where("userId").is(userId);
        Query query = Query.query(criteria).skip((page -1 ) * pagesize).limit(pagesize)
                .with(Sort.by(Sort.Order.desc("created")));
        List<Movement> movements = mongoTemplate.find(query, Movement.class);
        return new PageResult(page,pagesize,0l,movements);
    }
}
