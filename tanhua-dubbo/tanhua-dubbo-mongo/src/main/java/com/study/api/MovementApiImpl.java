package com.study.api;

import cn.hutool.core.collection.CollUtil;
import com.study.mongo.Friend;
import com.study.mongo.Movement;
import com.study.mongo.MovementTimeLine;
import com.study.utils.IdWorker;
import com.study.utils.TimeLineService;
import com.study.vo.PageResult;
import org.apache.dubbo.config.annotation.DubboService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
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

    /**
     * 查询当前用户好友发布的所有动态
     * @param friendId:当前操作用户id
     */
    public List<Movement> findFriendMovements(Integer page, Integer pagesize, Long friendId) {
        //1、根据friendId查询时间线表
        Query query = Query.query(Criteria.where("friendId").is(friendId))
                .skip((page -1) * pagesize).limit(pagesize).with(Sort.by(Sort.Order.desc("created")));
        List<MovementTimeLine> lineList = mongoTemplate.find(query, MovementTimeLine.class);
        //2、提取动态id列表
        List<ObjectId> list = CollUtil.getFieldValues(lineList, "movementId", ObjectId.class);
        //3、根据动态id查询动态详情
        Query movementQuery = Query.query(Criteria.where("id").in(list));
        return mongoTemplate.find(movementQuery,Movement.class);
    }

    //随机查询多条数据
    public List<Movement> randomMovements(Integer counts) {
        //1、创建统计对象，设置统计参数
        TypedAggregation aggregation = Aggregation.newAggregation(Movement.class,Aggregation.sample(counts));
        //2、调用mongoTemplate方法统计
        AggregationResults<Movement> results = mongoTemplate.aggregate(aggregation, Movement.class);
        //3、获取统计结果
        return results.getMappedResults();
    }

    //根据pid查询
    public List<Movement> findMovementsByPids(List<Long> pids) {
        Query query = Query.query(Criteria.where("pid").in(pids));
        return mongoTemplate.find(query,Movement.class);
    }

    @Override
    public Movement findById(String movementId) {
        return mongoTemplate.findById(movementId,Movement.class);
    }
}
