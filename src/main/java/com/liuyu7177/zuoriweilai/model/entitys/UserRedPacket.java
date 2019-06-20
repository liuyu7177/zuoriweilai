package com.liuyu7177.zuoriweilai.model.entitys;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by liuyu7177 On 2019/5/22
 */
public class UserRedPacket implements Serializable {
    private Long id;
    private Long redPacketId;
    private Long userId;
    private Double amount;
    private Timestamp grabTime;
    private String note;

    public UserRedPacket() {

    }

    /**
     *
     * @param redPacketId 红包Id
     * @param userId 用户Id
     * @param amount 单个红包价格
     * @param grabTime 抢到红包的时间
     * @param note 备注
     */
    public UserRedPacket(Long redPacketId, Long userId, Double amount, Timestamp grabTime, String note) {
        this.redPacketId = redPacketId;
        this.userId = userId;
        this.amount = amount;
        this.grabTime = grabTime;
        this.note = note;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRedPacketId() {
        return redPacketId;
    }

    public void setRedPacketId(Long redPacketId) {
        this.redPacketId = redPacketId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getGrabTime() {
        return grabTime;
    }

    public void setGrabTime(Timestamp grabTime) {
        this.grabTime = grabTime;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "UserRedPacket{" +
                "id=" + id +
                ", redPacketId=" + redPacketId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", grabTime=" + grabTime +
                ", note='" + note + '\'' +
                '}';
    }
}
