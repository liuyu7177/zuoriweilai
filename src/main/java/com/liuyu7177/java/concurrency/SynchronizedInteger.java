package com.liuyu7177.java.concurrency;

/**
 * 测试以后 好像也不安全
 * Created by liuyu7177 On 2019/5/31
 */
public class SynchronizedInteger implements IntegerInterface {
    private int value;

    public synchronized int getValue() {
            return value;
    }

    public synchronized void setValue(int value) {
            this.value = value;
    }
}
