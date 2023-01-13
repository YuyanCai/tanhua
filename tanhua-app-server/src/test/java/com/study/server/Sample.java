package com.study.server;

import com.study.autoconfig.template.SmsTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;

/**
 * @author: xiaocai
 * @since: 2023/01/13/16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class Sample {
    @Resource
    private SmsTemplate smsTemplate;

    @Test
    public void testSendSms(){
        smsTemplate.sendMessage("*","哈哈哈哈哈哈哈或");
    }
}
