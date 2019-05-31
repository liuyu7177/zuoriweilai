package com.liuyu7177.java.concurrency;

/**
 * 可变的整数
 * Created by liuyu7177 On 2019/5/31
 */
public class MutableInteger implements IntegerInterface{

    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
