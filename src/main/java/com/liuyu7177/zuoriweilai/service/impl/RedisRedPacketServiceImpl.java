package com.liuyu7177.zuoriweilai.service.impl;

import com.liuyu7177.zuoriweilai.framework.utils.DateUtils;
import com.liuyu7177.zuoriweilai.model.entity.UserRedPacket;
import com.liuyu7177.zuoriweilai.service.RedisRedPacketService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuyu7177 On 2019/5/24
 */
@Service
public class RedisRedPacketServiceImpl implements RedisRedPacketService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String PREFIX = "red_packet_list_";

    //每次取出1000条，避免一次取出太多消耗太多内存
    private static final int TIME_SIZE = 1000;

    @Autowired
    private RedisTemplate redisTemplate = null;

    @Autowired
    private DataSource dataSource = null;

    @Override
    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
        try {
            logger.info("开始保存数据");
            Long start = System.currentTimeMillis();
            BoundListOperations ops = redisTemplate.boundListOps(PREFIX + redPacketId);
            Long size = ops.size();
            //循环次数
            Long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
            //总共插入的红包个数
            int count = 0;
            List<UserRedPacket> userRedPacketList = new ArrayList<>(TIME_SIZE);

            for (int i = 0; i < times; i++) {
                List userIdList = null;
                if (i == 0) {
                    userIdList = ops.range(0, TIME_SIZE);
                } else {
                    userIdList = ops.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
                }
                userRedPacketList.clear();
                //System.out.println(userIdList.get(0).getClass());
                for (int j = 0; j < userIdList.size(); j++) {
                    String args = userIdList.get(j).toString();
                    String[] arr = args.split("-");
                    String userIdStr = arr[0];
                    String timeStr = arr[1];
                    Long userId = Long.parseLong(userIdStr);
                    Long time = Long.parseLong(timeStr);
                    UserRedPacket userRedPacket = new UserRedPacket(redPacketId, userId, unitAmount, new Timestamp(time), "抢红包:" + redPacketId);
                    userRedPacketList.add(userRedPacket);
                }
                count += executeBatch(userRedPacketList);
            }
            redisTemplate.delete(PREFIX + redPacketId);
            Long end = System.currentTimeMillis();
            logger.info("保存数据结束，耗时：" + (end - start) + "毫秒(" + (end - start) / 1000 + ")秒,共" + count + "条记录被保存。");
        } catch (Exception e) {
            logger.info("异常消息："+e.getMessage());
        }
    }

    private int executeBatch(List<UserRedPacket> userRedPackets) {
        Connection conn = null;
        Statement stmt = null;
        int[] count = null;
        try {
            conn = dataSource.getConnection();
            conn.setAutoCommit(false);
            stmt = conn.createStatement();
            for (UserRedPacket userRedPacket : userRedPackets) {
                String sql = "UPDATE red_packet set Stock=Stock - 1 WHERE Id =" + userRedPacket.getUserId();
                // DateFormat df=new SimpleDateFormat("yyyy-MM-dd");
                //DateUtils.FormatToyyyyMMdd("");
                String sql2 = "insert into user_red_packet(RedPacketId, UserId, Amount, GrabTime, Note) " +
                        " VALUES (" + userRedPacket.getRedPacketId() + "," + userRedPacket.getUserId() + "," +
                        +userRedPacket.getAmount() + ",'"
                        + DateUtils.FormatToyyyyMMddHHmmss(userRedPacket.getGrabTime()) + "','"
                        + userRedPacket.getNote() + "')";
                stmt.addBatch(sql);
                stmt.addBatch(sql2);
            }
            count = stmt.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("抢红包批量执行程序错误");
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return count.length/2;
    }
}
