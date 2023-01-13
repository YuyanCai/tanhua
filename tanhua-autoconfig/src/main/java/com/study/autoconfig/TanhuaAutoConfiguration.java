package com.study.autoconfig;

import com.study.autoconfig.config.SmsProperties;
import com.study.autoconfig.template.SmsTemplate;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: xiaocai
 * @since: 2023/01/13/16:51
 */
@EnableConfigurationProperties({
        SmsProperties.class
})
public class TanhuaAutoConfiguration {
    @Bean
    public SmsTemplate smsTemplate(SmsProperties smsProperties){
        return new SmsTemplate(smsProperties);
    }
}
