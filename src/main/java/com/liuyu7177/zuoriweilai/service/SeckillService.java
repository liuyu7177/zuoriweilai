package com.liuyu7177.zuoriweilai.service;

import com.liuyu7177.zuoriweilai.framework.exceptions.RepeatKillException;
import com.liuyu7177.zuoriweilai.framework.exceptions.SeckillCloseException;
import com.liuyu7177.zuoriweilai.framework.exceptions.SeckillException;
import com.liuyu7177.zuoriweilai.model.dtos.Exposer;
import com.liuyu7177.zuoriweilai.model.dtos.SeckillExecution;
import com.liuyu7177.zuoriweilai.model.entitys.Seckill;

import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/8
 */
public interface SeckillService {

    /**
     * 查询所有秒杀记录
     *
     * @return 返回符合条件的秒杀记录
     */
    List<Seckill> getSeckillList();

    /**
     * 查询单个秒杀记录
     *
     * @param seckillId 秒杀记录Id
     * @return 符合条件的记录
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输入秒杀接口地址
     * 输出秒杀接口地址
     *
     * @param seckillId 秒杀Id
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀
     *
     * @param seckillId 秒杀Id
     * @param userPhone 用户手机号
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
            throws SeckillException, RepeatKillException, SeckillCloseException;

    /**
     * 执行秒杀 通过存储过程
     *
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
