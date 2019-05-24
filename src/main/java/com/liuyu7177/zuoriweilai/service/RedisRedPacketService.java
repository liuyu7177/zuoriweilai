package com.liuyu7177.zuoriweilai.service;

/**
 * Created by liuyu7177 On 2019/5/24
 */
public interface RedisRedPacketService {

    /**
     * 保存Redis抢红包列表
     * @param redPacketId
     * @param unitAmount
     */
    public void saveUserRedPacketByRedis(Long redPacketId,Double unitAmount);

}
