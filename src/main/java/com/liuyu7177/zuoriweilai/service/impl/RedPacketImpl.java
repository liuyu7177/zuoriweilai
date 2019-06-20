package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.dao.RedPacketDao;
import com.liuyu7177.zuoriweilai.model.entitys.RedPacket;
import com.liuyu7177.zuoriweilai.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@Service
public class RedPacketImpl implements RedPacketService {

    @Autowired
    private RedPacketDao redPacketDao = null;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(Long id) {
        return redPacketDao.getRedPacket(id);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED,propagation = Propagation.REQUIRED)
    public int decreaseRedPacket(Long id) {
        return redPacketDao.decreaseRedPacket(id);
    }


}
