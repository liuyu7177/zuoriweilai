package com.liuyu7177.java.concurrency;

/**
 * 多线程类
 * Created by liuyu7177 On 2019/5/31
 */
public class MultiThreading extends Thread {
    public static IntegerInterface integer;

    public static IntegerInterface getInteger() {
        return integer;
    }

    public static void setInteger(IntegerInterface integer) {
        MultiThreading.integer = integer;
    }


    public void run() {
        for (int i = 0; i < 1000; i++) {
            integer.autoIncrement();
        }
        System.out.println("循环执行完："+Thread.currentThread().getName()+" "+integer.getValue());
    }
}
