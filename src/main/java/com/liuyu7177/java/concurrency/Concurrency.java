package com.liuyu7177.java.concurrency;

/**
 * Created by liuyu7177 On 2019/5/31
 */
public class Concurrency {
    public static void main(String[] args) throws InterruptedException {
        IntegerInterface integerInterface=new MutableInteger();
        //IntegerInterface integerInterface=new SynchronizedInteger();
        MultiThreading.setInteger(integerInterface);
        MultiThreading t1=new  MultiThreading();
        MultiThreading t2=new  MultiThreading();
        MultiThreading t3=new  MultiThreading();
        MultiThreading t4=new  MultiThreading();
        MultiThreading t5=new  MultiThreading();
        MultiThreading t6=new  MultiThreading();
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        Thread.sleep(1000);
        System.out.println(integerInterface.getValue());
    }
}
