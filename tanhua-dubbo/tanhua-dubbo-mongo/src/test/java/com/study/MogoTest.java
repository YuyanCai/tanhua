package com.study;

import com.study.mongo.Friend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author: xiaocai
 * @since: 2023/01/31/15:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DubboMongoApplication.class)
public class MogoTest {

    @Resource
    private MongoTemplate mongoTemplate;

    @Test
    public void  testSave(){
        Friend friend = new Friend();
        friend.setFriendId(106L);
        mongoTemplate.save(friend);
    }


}
