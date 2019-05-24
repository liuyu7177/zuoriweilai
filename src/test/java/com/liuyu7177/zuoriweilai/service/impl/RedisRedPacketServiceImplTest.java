package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.service.RedisRedPacketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuyu7177 On 2019/5/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-web.xml",
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-redis.xml"})
public class RedisRedPacketServiceImplTest {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedisRedPacketService redisRedPacketService;

    @Test
    public void saveUserRedPacketByRedis() {
        redisRedPacketService.saveUserRedPacketByRedis(1L, 1d);
    }

    @Test
    public void testAsync() {
        logger.debug("开始调用");
        redisRedPacketService.saveUserRedPacketByRedis(1L, 10d);
        logger.debug("结束调用");
        Long start = System.currentTimeMillis();
//        while (true) {
//
//        }
    }
}