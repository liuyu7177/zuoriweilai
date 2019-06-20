package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.model.entitys.UserInfo;
import com.liuyu7177.zuoriweilai.service.RedisTestService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by liuyu7177 On 2019/5/13
 */
@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Cacheable(value = "redisCacheManager" ,key = "'redis_userInfo_'+#id")
    public UserInfo getUserInfo(int id) {
        UserInfo u = new UserInfo(id, "liuyu7177"+ id, "note");
        return u;
    }

    @CachePut(value = "redisCacheManager" ,key = "'redis_userInfo_'+#id")
    public UserInfo getUserInfoByPut(int id) {
        UserInfo u = new UserInfo(id, "liuyu7177"+ id+"Put", "note");
        return u;
    }
    @CacheEvict(value = "redisCacheManager" ,key = "'redis_userInfo_'+#id")
    public void deleteById(int id) {
        System.out.println("刪除");
    }

}
