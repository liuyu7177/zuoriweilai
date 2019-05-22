package com.liuyu7177.zuoriweilai.model.entity;

import java.io.Serializable;

/**
 * Created by liuyu7177 On 2019/5/20
 */
public class UserInfo implements Serializable
{

    private static final long serialVersionUID = 3558397317682600072L;
    private int userId;
    private String userName;
    private  String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public UserInfo() {
    }

    /**
     *
     * @param userId 用户id
     * @param userName 用户名
     * @param note 备注
     */
    public UserInfo(int userId, String userName, String note) {
        this.userId = userId;
        this.userName = userName;
        this.note = note;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                '}';
    }
}
