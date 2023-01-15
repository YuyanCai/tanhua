package com.study.autoconfig.config;

import com.study.autoconfig.entity.SmsProperties;
import com.study.autoconfig.template.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * @author: xiaocai
 * @since: 2023/01/15/11:53
 */

@EnableConfigurationProperties({
        SmsProperties.class
})
public class TanhuaAutoConfiguration {
    @Bean
    public SmsTemplate smsTemplate(SmsProperties properties) {
        return new SmsTemplate(properties);
    }
}





