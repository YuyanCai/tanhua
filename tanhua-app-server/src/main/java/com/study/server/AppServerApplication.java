package com.study.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author: xiaocai
 * @since: 2023/01/13/16:56
 */

@SpringBootApplication
public class AppServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppServerApplication.class);
    }
}
