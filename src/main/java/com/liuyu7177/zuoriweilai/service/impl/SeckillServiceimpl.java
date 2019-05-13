package com.liuyu7177.zuoriweilai.service.impl;


import com.liuyu7177.zuoriweilai.dao.SeckillDao;
import com.liuyu7177.zuoriweilai.dao.SuccessKilledDao;
import com.liuyu7177.zuoriweilai.dao.cache.RedisDao;
import com.liuyu7177.zuoriweilai.framework.exceptions.RepeatKillException;
import com.liuyu7177.zuoriweilai.framework.exceptions.SeckillCloseException;
import com.liuyu7177.zuoriweilai.framework.exceptions.SeckillException;
import com.liuyu7177.zuoriweilai.model.dto.Exposer;
import com.liuyu7177.zuoriweilai.model.dto.SeckillExecution;
import com.liuyu7177.zuoriweilai.model.entity.Seckill;
import com.liuyu7177.zuoriweilai.model.entity.SuccessKilled;
import com.liuyu7177.zuoriweilai.model.enums.SeckillStateEnum;
import com.liuyu7177.zuoriweilai.service.SeckillService;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuyu7177 On 2019/5/8
 */
@Service
public class SeckillServiceimpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;

    private final String slat = "fdaf43242^&*^*()>><}{LL";

    public List<Seckill> getSeckillList() {
        List<Seckill> list = seckillDao.queryAll(0, 4);
        return list;
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (seckill == null) {
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) {
                return new Exposer(false, seckillId);
            } else {
                redisDao.putSeckill(seckill);
            }
        }
        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), startTime.getTime(), endTime.getTime());
        }
        String md5 = getMD5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        return DigestUtils.md5DigestAsHex(base.getBytes());
    }

    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime = new Date();
        try {

            /*int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                throw new SeckillCloseException("seckill is closed");
            } else {
                int insertCount = successKilledDao.insertSuccessKillEd(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("seckill repeated");
                } else {
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
                }
            }*/
            int insertCount = successKilledDao.insertSuccessKillEd(seckillId, userPhone);
            if (insertCount <= 0) {
                throw new RepeatKillException("seckill repeated");
            } else {
                int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
                if (updateCount <= 0) {
                    throw new SeckillCloseException("seckill is closed");
                }
                SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, successKilled);
            }
        } catch (SeckillCloseException e) {
            throw e;
        } catch (RepeatKillException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new SeckillException("seckill inner error:" + e.getMessage());
        }
    }

    public SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            return new SeckillExecution(seckillId, SeckillStateEnum.DATA_REWRITE);
        }
        Date nowTime = new Date();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("seckillId", seckillId);
        paramMap.put("phone", userPhone);
        paramMap.put("killTime", nowTime);
        paramMap.put("result", null);
        try {
            seckillDao.killByProcedure(paramMap);

            int result = MapUtils.getInteger(paramMap, "result", -2);
            if (result == 1) {
                SuccessKilled sk = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                return new SeckillExecution(seckillId, SeckillStateEnum.SUCCESS, sk);
            } else {
                return new SeckillExecution(seckillId, SeckillStateEnum.stateOf(result));
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new SeckillExecution(seckillId, SeckillStateEnum.INNER_ERROR);
        }
    }
}
