package com.liuyu7177.zuoriweilai.service;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public interface UserRedPacketService {
    /**
     * 插入抢红包信息 有超发情况
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacket(Long redPacketId,Long userId);

    /**
     * 插入抢红包信息 乐观锁机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersion(Long redPacketId,Long userId);

    /**
     * 插入抢红包信息 悲观锁机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForUpdate(Long redPacketId,Long userId);

    /**
     * 插入抢红包信息 乐观锁100毫秒重入机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersionAndTimeMillis(Long redPacketId, Long userId);

    /**
     * 插入抢红包信息 乐观锁3次重入机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersionAndThrice(Long redPacketId, Long userId);

    /**
     * 用存储过程执行抢红包
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketByProcedure(Long redPacketId, Long userId);

    /**
     * 通过redis实现抢红包
     * @param redPacketId 红包编号
     * @param userId 用户编号
     * @return
     * 0-没有库存，失败
     * 1-成功，且不是最后一个红包
     * 2-最后，且是最后一个红包
     */
    public Long grabRedPacketByRedis(Long redPacketId,Long userId);
}
