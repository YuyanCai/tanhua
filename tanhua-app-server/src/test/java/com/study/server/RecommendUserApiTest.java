package com.study.server;


import com.jayway.jsonpath.Criteria;
import com.study.api.RecommendUserApi;
import com.study.mongo.RecommendUser;
import com.tencentcloudapi.tci.v20190318.models.Person;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class RecommendUserApiTest {

    @DubboReference
    private RecommendUserApi userApi;

    @Test
    public void testFindByMobile() {
        RecommendUser recommendUser = userApi.queryWithMaxScore(106L);
        System.out.println(recommendUser);
    }


}
