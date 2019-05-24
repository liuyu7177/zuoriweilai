package com.liuyu7177.zuoriweilai.dao;

import com.liuyu7177.zuoriweilai.model.entity.RedPacket;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liuyu7177 On 2019/5/22
 */

public interface RedPacketDao {
    /**
     * 获取红包信息
     * @param id
     * @return
     */
    public RedPacket getRedPacket(Long id);

    /**
     * 获取红包信息 悲观锁机制
     * @param id
     * @return
     */
    public RedPacket getRedPacketForUpdate(Long id);



    /**
     * 扣減抢红包数
     * @param id
     * @return
     */
    public int decreaseRedPacket(Long id);

    /**
     * 扣減抢红包数 乐观锁机制
     * @param id
     * @return
     */
    public int decreaseRedPacketForVersion(@Param("id")Long id,@Param("version") Long version);
}
