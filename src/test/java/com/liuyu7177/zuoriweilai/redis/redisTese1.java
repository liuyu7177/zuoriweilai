package com.liuyu7177.zuoriweilai.redis;

import com.liuyu7177.zuoriweilai.model.entity.UserInfo;
import com.liuyu7177.zuoriweilai.service.RedisTestService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-redis.xml","classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class redisTese1 {

    @Autowired
    private JedisPoolConfig poolConfig;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisTestService redisTestService;

    @Test
    public void TestRedis() {
        redisTestService.deleteById(1);

        UserInfo u1 = redisTestService.getUserInfo(1);
        System.out.println(u1);

        UserInfo u2 = redisTestService.getUserInfo(1);
        System.out.println(u2);

        UserInfo u3 = redisTestService.getUserInfoByPut(1);
        System.out.println(u3);

        UserInfo u4 = redisTestService.getUserInfo(1);
        System.out.println(u4);

        UserInfo u5 = redisTestService.getUserInfoByPut(1);
        System.out.println(u5);

        redisTestService.deleteById(1);
        UserInfo u6 = redisTestService.getUserInfoByPut(1);
        System.out.println(u6);

    }

    @Test
    public void TestChan()
    {
        redisTemplate.convertAndSend("chat","I am lazy!!");
    }
    @Test
    @Ignore
    public void testRedisPipeline() {
        JedisPool pool = new JedisPool(poolConfig, "127.0.0.1");
        Jedis jedis = pool.getResource();
        Pipeline pipeline=jedis.pipelined();
        long start = System.currentTimeMillis();
        for (int i=0;i<100000;i++)
        {
            int j=i+1;
            pipeline.set("pipe_key_"+j,"pipe_value_"+j);
            pipeline.get("pipe_key_"+j);
        }
        List result=pipeline.syncAndReturnAll();
        long end = System.currentTimeMillis();
        for (Object str:result)
        {
            System.out.println(str.toString());
        }

        System.out.println("耗時：" + (end-start) + "毫秒");
    }

    @Test
    @Ignore
    public void testRedisMulti() {
        SessionCallback callback = new SessionCallback() {
            @Override
            public Object execute(RedisOperations ops) throws DataAccessException {
                ops.multi();
                String key1 = "key1";
                ops.boundValueOps(key1).set("value1");
                String value = (String) ops.boundValueOps("key1").get();
                System.out.println("1-key1:" + value);
                value = (String) redisTemplate.opsForValue().get("key1");
                System.out.println("2-key1:" + value);
                ops.exec();
                value = (String) redisTemplate.opsForValue().get("key1");
                return value;
            }
        };
        String value = (String) redisTemplate.execute(callback);
        System.out.println("3-key1:" + value);
    }

    @Test
    @Ignore
    public void testSaveUserInfoToRedis() {
        int i = 0;
        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start >= 1000) {
                break;
            }
            i++;
            UserInfo u = new UserInfo(i, "liuyu7177", "redisNote");
            redisTemplate.opsForValue().set("role_" + i, u);
            UserInfo u1 = (UserInfo) redisTemplate.opsForValue().get("role_" + i);
        }
        System.out.println("redis每秒操作：" + i + "次");

    }

    @Test
    @Ignore
    public void saveUserInfoToRedis() {
        UserInfo u = new UserInfo(1, "liuyu7177", "redisNote");
        redisTemplate.opsForValue().set("role_1", u);
        UserInfo u1 = (UserInfo) redisTemplate.opsForValue().get("role_1");
        System.out.println(u1);
    }

    @Test
    @Ignore
    public void ConnRedisByXml() {
        JedisPool pool = new JedisPool(poolConfig, "127.0.0.1");
        Jedis jedis = pool.getResource();
        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {
            jedis.close();
        }
        System.out.println("redis每秒操作：" + i + "次");
    }

    @Test
    @Ignore
    public void ConnRedis() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(10);
        poolConfig.setMaxTotal(50);
        poolConfig.setMaxWaitMillis(20000);
        //JedisPool pool=new JedisPool(poolConfig,"47.93.232.171");
        JedisPool pool = new JedisPool(poolConfig, "127.0.0.1");
        Jedis jedis = pool.getResource();
        //Jedis jedis = new Jedis("47.93.232.171", 6379);
        // Jedis jedis = new Jedis("127.0.0.1", 6379);

        int i = 0;
        try {
            long start = System.currentTimeMillis();
            while (true) {
                long end = System.currentTimeMillis();
                if (end - start >= 1000) {
                    break;
                }
                i++;
                jedis.set("test" + i, i + "");
            }
        } finally {
            jedis.close();
        }
        System.out.println("redis每秒操作：" + i + "次");
    }
}
