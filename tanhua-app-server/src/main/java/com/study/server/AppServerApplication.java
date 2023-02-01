package com.study.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author: xiaocai
 * @since: 2023/01/13/16:56
 */

@SpringBootApplication(exclude = {
        MongoAutoConfiguration.class,
        MongoDataAutoConfiguration.class
}) //排除mongo的自动配置
public class AppServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppServerApplication.class);
    }
}
