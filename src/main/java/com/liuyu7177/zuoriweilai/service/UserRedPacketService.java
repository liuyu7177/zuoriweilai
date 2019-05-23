package com.liuyu7177.zuoriweilai.service;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public interface UserRedPacketService {
    /**
     * 插入抢红包信息
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacket(Long redPacketId,Long userId);

    /**
     * 插入抢红包信息
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersion(Long redPacketId,Long userId);



}
