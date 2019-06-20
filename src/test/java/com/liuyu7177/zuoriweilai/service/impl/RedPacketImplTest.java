package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.model.entitys.RedPacket;
import com.liuyu7177.zuoriweilai.service.RedPacketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-redis.xml","classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class RedPacketImplTest {

    public Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RedPacketService redPacketService;
    @Test
    public void getRedPacket() {
        RedPacket redPacket=redPacketService.getRedPacket(1L);
        System.out.println(redPacket);
        logger.error(redPacket.toString());
    }

    @Test
    public void decreaseRedPacket() {
        int  affectRow=redPacketService.decreaseRedPacket(1L);
        System.out.println("受影响行数："+affectRow);
        logger.error("受影响行数："+affectRow);
    }
}