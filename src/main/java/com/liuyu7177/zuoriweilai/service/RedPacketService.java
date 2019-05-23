package com.liuyu7177.zuoriweilai.service;

import com.liuyu7177.zuoriweilai.model.entity.RedPacket;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public interface RedPacketService {
    /**
     * 获取红包信息
     * @param id
     * @return
     */
    public RedPacket getRedPacket(Long id);

    /**
     * 扣減抢红包数
     * @param id
     * @return
     */
    public int decreaseRedPacket(Long id);

}
