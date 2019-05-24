package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.dao.RedPacketDao;
import com.liuyu7177.zuoriweilai.dao.UserRedPacketUniqueDao;
import com.liuyu7177.zuoriweilai.model.entity.RedPacket;
import com.liuyu7177.zuoriweilai.model.entity.UserRedPacketUnique;
import com.liuyu7177.zuoriweilai.service.UserRedPacketUniqueService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@Service
public class UserRedPacketUniqueServiceImpl implements UserRedPacketUniqueService {

    @Autowired
    private UserRedPacketUniqueDao userRedPacketUniqueDao = null;

    @Autowired
    private RedPacketDao redPacketDao = null;

    private static final int FAILED = 0;

    /**
     * 抢红包 有超发的情况
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacket(Long redPacketId, Long userId) {
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        if (redPacket.getStock() > 0) {
            redPacketDao.decreaseRedPacket(redPacketId);
            UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }

    /**
     * 抢红包 悲观锁机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForUpdate(Long redPacketId, Long userId) {
        RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
        if (redPacket.getStock() > 0) {
            redPacketDao.decreaseRedPacket(redPacketId);
            UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }

    /**
     * 乐观锁机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForVersion(Long redPacketId, Long userId) {
        RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
        if (redPacket.getStock() > 0) {
            int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
            if (update <= 0) {
                return FAILED;
            }
            UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
            return result;
        }
        return FAILED;
    }

    /**
     * 插入抢红包信息 乐观锁100毫秒重入机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForVersionAndTimeMillis(Long redPacketId, Long userId) {
        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 100) {
                return FAILED;
            }
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            if (redPacket.getStock() > 0) {
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if (update <= 0) {
                    continue;
                }
                UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包" + redPacketId);
                int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
                return result;
            }
            return FAILED;
        }
    }

    /**
     * 插入抢红包信息 乐观锁33次重入机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForVersionAndThrice(Long redPacketId, Long userId) {
        for (int i = 0; i < 3; i++) {
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            if (redPacket.getStock() > 0) {
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if (update <= 0) {
                    continue;
                }
                UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包" + redPacketId);
                int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
                return result;
            }
            return FAILED;
        }
        return FAILED;
    }
    /*************************唯一索引 调整代码顺序测试*********************************************/

    /**
     * 插入抢红包信息 悲观锁机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForUpdateOrder(Long redPacketId, Long userId, Double amount) {

        UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
        userRedPacket.setRedPacketId(redPacketId);
        userRedPacket.setUserId(userId);
        userRedPacket.setAmount(amount);
        userRedPacket.setNote("抢红包" + redPacketId);
        int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
        if (result <= 0) {
            //重复抢购，直接返回失败
            throw new RuntimeException("重复抢购");
        }
        RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
        if (redPacket.getStock() > 0) {
            int update = redPacketDao.decreaseRedPacket(redPacketId);
            if (update <= 0) {
                throw new RuntimeException("");
            } else {
                return update;
            }
        }
        return FAILED;
    }

    /**
     * 插入抢红包信息 乐观锁机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForVersionOrder(Long redPacketId, Long userId, Double amount) {
        UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
        userRedPacket.setRedPacketId(redPacketId);
        userRedPacket.setUserId(userId);
        userRedPacket.setAmount(amount);
        userRedPacket.setNote("抢红包" + redPacketId);
        int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
        if (result <= 0) {
            //重复抢购，直接返回失败
            throw new RuntimeException("重复抢购");
        }
        RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
        if (redPacket.getStock() > 0) {
            int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
            if (update <= 0) {
                throw new RuntimeException("");
            } else {
                return update;
            }
        }
        return FAILED;
    }

    /**
     * 插入抢红包信息 乐观锁100毫秒重入机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForVersionAndTimeMillisOrder(Long redPacketId, Long userId, Double amount) {

        UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
        userRedPacket.setRedPacketId(redPacketId);
        userRedPacket.setUserId(userId);
        userRedPacket.setAmount(amount);
        userRedPacket.setNote("抢红包" + redPacketId);
        int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
        if (result <= 0) {
            //重复抢购，直接返回失败
            throw new RuntimeException("重复抢购");
        }
        long start = System.currentTimeMillis();
        int update =FAILED;
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 100) {
                throw new RuntimeException("重复抢购");
            }
            RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
            if (redPacket.getStock() > 0) {
                update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if (update <= 0) {
                    continue;
                } else {
                    return update;
                }
            }else {
                throw new RuntimeException("重复抢购");
            }
        }
    }

    /**
     * 插入抢红包信息 乐观锁3次重入机制
     *
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int grabRedPacketForVersionAndThriceOrder(Long redPacketId, Long userId, Double amount) {
        UserRedPacketUnique userRedPacket = new UserRedPacketUnique();
        userRedPacket.setRedPacketId(redPacketId);
        userRedPacket.setUserId(userId);
        userRedPacket.setAmount(amount);
        userRedPacket.setNote("抢红包" + redPacketId);
        int result = userRedPacketUniqueDao.grabRedPacket(userRedPacket);
        if (result <= 0) {
            //重复抢购，直接返回失败
            throw new RuntimeException("重复抢购");
        }

        int update =FAILED;
        for (int i = 0; i < 3; i++) {
            RedPacket redPacket = redPacketDao.getRedPacketForUpdate(redPacketId);
            if (redPacket.getStock() > 0) {
                update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if (update <= 0) {
                    continue;
                } else {
                    return update;
                }
            }else {
                throw new RuntimeException("重复抢购");
            }
        }
        throw new RuntimeException("重复抢购");
    }

    /**
     * 用存储过程执行抢红包
     * @param redPacketId
     * @param userId
     * @return
     */
    @Override
    public int grabRedPacketByProcedure(Long redPacketId, Long userId) {
        Date nowTime = new Date();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("redPacketId", redPacketId);
        paramMap.put("userId", userId);
        paramMap.put("result", null);
        try {
            userRedPacketUniqueDao.grabRedPacketByProcedure(paramMap);
            int result = MapUtils.getInteger(paramMap, "result", -2);
            return  result;
        } catch (Exception e) {
            return FAILED;
        }
    }
}
