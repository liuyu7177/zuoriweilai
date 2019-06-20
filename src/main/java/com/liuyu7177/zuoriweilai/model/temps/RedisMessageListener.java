package com.liuyu7177.zuoriweilai.model.temps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public class RedisMessageListener implements MessageListener {

    @Autowired
    private RedisTemplate redisTemplate;

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void onMessage(Message message, byte[] bytes) {
        byte[] body = message.getBody();
        String msgBody = (String) getRedisTemplate().getValueSerializer().deserialize(body);
        System.out.println("msgBody:"+msgBody);

        byte[] channel = message.getChannel();
        String channelStr = (String) getRedisTemplate().getStringSerializer().deserialize(channel);
        System.out.println("channelStr:"+channelStr);

        String bytesStr=new String(bytes);
        System.out.println("bytesStr:"+bytesStr);
    }
}
