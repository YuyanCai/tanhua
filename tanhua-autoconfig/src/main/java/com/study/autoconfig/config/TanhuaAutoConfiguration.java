package com.study.autoconfig.config;

import com.study.autoconfig.entity.AipFaceProperties;
import com.study.autoconfig.entity.OssProperties;
import com.study.autoconfig.entity.SmsProperties;
import com.study.autoconfig.template.AipFaceTemplate;
import com.study.autoconfig.template.OssTemplate;
import com.study.autoconfig.template.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author: xiaocai
 * @since: 2023/01/15/11:53
 */

@EnableConfigurationProperties({
        SmsProperties.class,
        OssProperties.class,
        AipFaceProperties.class
})
public class TanhuaAutoConfiguration {
    @Bean
    public SmsTemplate smsTemplate(SmsProperties properties) {
        return new SmsTemplate(properties);
    }

    @Bean
    public OssTemplate ossTemplate(OssProperties properties) {
        return new OssTemplate(properties);
    }

    @Bean
    public AipFaceTemplate aipFaceTemplate() {
        return new AipFaceTemplate();
    }

}





