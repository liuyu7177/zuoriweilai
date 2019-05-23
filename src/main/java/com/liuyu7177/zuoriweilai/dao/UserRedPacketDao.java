package com.liuyu7177.zuoriweilai.dao;

import com.liuyu7177.zuoriweilai.model.entity.UserRedPacket;

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
}
