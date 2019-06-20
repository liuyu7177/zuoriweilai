package com.liuyu7177.zuoriweilai.dao;

import com.liuyu7177.zuoriweilai.model.entitys.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by liuyu7177 On 2019/5/7
 *
 * @author liuyu
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细，可过滤重复秒杀
     *
     * @param seckillId 秒杀Id
     * @param userPhone 用户手机号码
     * @return 表示插入的行数
     */
    int insertSuccessKillEd(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

    /**
     * 根据Id查询SuccessKilled并携带秒杀产品对象
     *
     * @param seckillId 秒杀Id
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId, @Param("userPhone") long userPhone);

}
