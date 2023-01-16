package com.study.server;

import com.study.api.UserApi;
import com.study.autoconfig.template.SmsTemplate;
import com.study.entiy.User;
import io.jsonwebtoken.*;
import org.apache.dubbo.config.annotation.DubboReference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: xiaocai
 * @since: 2023/01/13/16:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppServerApplication.class)
public class Sample {
    @Resource
    private SmsTemplate smsTemplate;

    @DubboReference
    UserApi userApi;

    @Test
    public void testFindByMobile(){
        User byMobile = userApi.findByMobile("1111");
        System.out.println(byMobile);
    }

    @Test
    public void testSendSms(){
        smsTemplate.sendMessage("17630345765","5555");
    }

    /**
     * jwt是经过加密处理的字符串！
     * 分为三部分：
     *  1.指定加密算法
     *  2.指定加密内容
     *  3.前两部分加密得到，是校验部分
     */
    @Test
    public void testCreateToken(){
        //生成token
        //1、准备数据
        Map map = new HashMap<>();
        map.put("id",1);
        map.put("mobile","17814930876");
//        2、使用JWT工具类生成token
        long now = System.currentTimeMillis();
        String token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, "tanhua")
                .setClaims(map)
                .setExpiration(new Date(now + 500000))
                .compact();
        System.out.println(token);
    }

    /**
     * 校验token
     */
    @Test
    public void testParseToken(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJtb2JpbGUiOiIxNzgxNDkzMDg3NiIsImlkIjoxLCJleHAiOjE2NzM3NzEzMDN9.2a1OuYnEvb8SZHl9dG4htPv_Y5ToSWE5q51PGTx36EM";

//        解析token
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey("tanhua")
                    .parseClaimsJws(token)
                    .getBody();
            Object id = claims.get("id");
            Object mobile = claims.get("mobile");
            System.out.println(id + "--" + mobile);
        } catch (ExpiredJwtException e) {
            System.out.println("token已过期");
        } catch (SignatureException e) {
            System.out.println("token不合法");
        }

    }







}
