package com.study.server;

import com.study.api.UserApi;
import com.study.autoconfig.template.OssTemplate;
import com.study.entiy.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author: xiaocai
 * @since: 2023/01/15/21:47
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class OssTest {
    @Resource
    OssTemplate ossTemplate;

    @Test
    public void testFindByMobile() throws FileNotFoundException {

        String path = "/Users/xiaocai/Desktop/cat.jpg";
        FileInputStream inputStream = new FileInputStream(new File(path));
        String imgUrl = ossTemplate.upload(path, inputStream);
        System.out.println(imgUrl);
    }
}
