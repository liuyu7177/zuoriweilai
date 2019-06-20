package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.dao.RedPacketDao;
import com.liuyu7177.zuoriweilai.dao.UserRedPacketDao;
import com.liuyu7177.zuoriweilai.model.entitys.RedPacket;
import com.liuyu7177.zuoriweilai.model.entitys.UserRedPacket;
import com.liuyu7177.zuoriweilai.service.RedisRedPacketService;
import com.liuyu7177.zuoriweilai.service.UserRedPacketService;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/22
 */
@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {

    @Autowired
    private UserRedPacketDao userRedPacketDao = null;

    @Autowired
    private RedPacketDao redPacketDao = null;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private RedisRedPacketService redisRedPacketService = null;

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
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketDao.grabRedPacket(userRedPacket);
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
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketDao.grabRedPacket(userRedPacket);
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
            UserRedPacket userRedPacket = new UserRedPacket();
            userRedPacket.setRedPacketId(redPacketId);
            userRedPacket.setUserId(userId);
            userRedPacket.setAmount(redPacket.getUnitAmount());
            userRedPacket.setNote("抢红包" + redPacketId);
            int result = userRedPacketDao.grabRedPacket(userRedPacket);
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
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包" + redPacketId);
                int result = userRedPacketDao.grabRedPacket(userRedPacket);
                return result;
            }
            return FAILED;
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
    public int grabRedPacketForVersionAndThrice(Long redPacketId, Long userId) {
        for (int i = 0; i < 3; i++) {
            RedPacket redPacket = redPacketDao.getRedPacket(redPacketId);
            if (redPacket.getStock() > 0) {
                int update = redPacketDao.decreaseRedPacketForVersion(redPacketId, redPacket.getVersion());
                if (update <= 0) {
                    continue;
                }
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包" + redPacketId);
                int result = userRedPacketDao.grabRedPacket(userRedPacket);
                return result;
            }
            return FAILED;
        }
        return FAILED;
    }

    /**
     * 用存储过程执行抢红包
     *
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
            userRedPacketDao.grabRedPacketByProcedure(paramMap);
            int result = MapUtils.getInteger(paramMap, "result", -2);
            return result;
        } catch (Exception e) {
            return FAILED;
        }
    }

    String script = " local listKey ='red_packet_list_'..KEYS[1] \n"
            + " local redPacket ='red_packet_'..KEYS[1] \n"
            + " local stock =tonumber(redis.call('hget',redPacket,'stock')) \n"
            + " if stock<=0 then return 0 end \n"
            + " stock=stock -1 \n"
            + " redis.call('hset',redPacket,'stock',tostring(stock)) \n"
            + " redis.call('rpush',listKey,ARGV[1]) \n"
            + " if stock==0 then return 2 end \n "
            + " return 1 \n";

    //redis 缓存之后的Lua脚本SHA1编码
    String sha1 = null;

    @Override
    public Long grabRedPacketByRedis(Long redPacketId, Long userId) {
        String args = userId + "-" + System.currentTimeMillis();
        Long result = null;
        Jedis jedis = (Jedis) redisTemplate.getConnectionFactory().getConnection().getNativeConnection();
        try {
            if (sha1 == null) {
                sha1 = jedis.scriptLoad(script);
            }
            Object res = jedis.evalsha(sha1, 1, redPacketId + "", args);
            result = (Long) res;
            if (result == 2) {
                String unitAmountStr = jedis.hget("red_packet_" + redPacketId, "unit_amount");
                Double unitAmount = Double.parseDouble(unitAmountStr);
                System.out.println("thread_name =" + Thread.currentThread().getName());
                redisRedPacketService.saveUserRedPacketByRedis(redPacketId, unitAmount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (jedis != null&&jedis.isConnected()) {
                jedis.close();
            }
        }
        return result;
    }
}
