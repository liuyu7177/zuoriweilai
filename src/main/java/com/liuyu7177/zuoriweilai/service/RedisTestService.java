package com.liuyu7177.zuoriweilai.service;

import com.liuyu7177.zuoriweilai.model.entity.UserInfo;

/**
 * Created by liuyu7177 On 2019/5/13
 */
public interface RedisTestService {
    public UserInfo getUserInfo(int id);
    public UserInfo getUserInfoByPut(int id);
    public void deleteById(int id);
}
