package com.liuyu7177.zuoriweilai.dao;

import com.liuyu7177.zuoriweilai.model.entitys.UserRedPacket;

import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public interface UserRedPacketDao {

    /**
     * 插入抢红包信息
     * @param userRedPacket
     * @return
     */
    public int grabRedPacket(UserRedPacket userRedPacket);

    /**
     * 使用存储过程执行秒杀
     *
     * @param paramMap
     */
    public void grabRedPacketByProcedure(Map<String, Object> paramMap);
}
