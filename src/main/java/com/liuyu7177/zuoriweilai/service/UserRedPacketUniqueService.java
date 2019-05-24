package com.liuyu7177.zuoriweilai.service;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public interface UserRedPacketUniqueService {
    /**
     * 插入抢红包信息 有超发情况
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacket(Long redPacketId, Long userId);

    /**
     * 插入抢红包信息 乐观锁机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersion(Long redPacketId, Long userId);

    /**
     * 插入抢红包信息 悲观锁机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForUpdate(Long redPacketId, Long userId);

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

    /*************************唯一索引 调整代码顺序测试*********************************************/

    /**
     * 插入抢红包信息 悲观锁机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForUpdateOrder(Long redPacketId, Long userId,Double amount);

    /**
     * 插入抢红包信息 乐观锁机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersionOrder(Long redPacketId, Long userId,Double amount);

    /**
     * 插入抢红包信息 乐观锁100毫秒重入机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersionAndTimeMillisOrder(Long redPacketId, Long userId,Double amount);


    /**
     * 插入抢红包信息 乐观锁3次重入机制
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketForVersionAndThriceOrder(Long redPacketId, Long userId,Double amount);

    /**
     * 用存储过程执行抢红包
     * @param redPacketId
     * @param userId
     * @return
     */
    public int grabRedPacketByProcedure(Long redPacketId, Long userId);
}
