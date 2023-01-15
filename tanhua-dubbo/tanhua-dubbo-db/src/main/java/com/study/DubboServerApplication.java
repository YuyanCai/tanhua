package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: xiaocai
 * @since: 2023/01/15/16:39
 */
@SpringBootApplication
//mapper扫描
@MapperScan("com.study.mapper")
public class DubboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboServerApplication.class,args);
    }
}


