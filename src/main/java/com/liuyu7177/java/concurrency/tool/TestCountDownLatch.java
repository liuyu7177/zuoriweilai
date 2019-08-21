package com.liuyu7177.java.concurrency.tool;

import com.liuyu7177.zuoriweilai.framework.utils.DateUtils;

import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 * Created by liuyu7177 On 2019/8/21
 */
public class TestCountDownLatch {

    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(2);

        for (int i = 0; i < 2; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        System.out.println("线程等待："+Thread.currentThread().getName()+":"+DateUtils.GetDateTimeNow());
                        startGate.await();
                        System.out.println("子线程开锁进入："+Thread.currentThread().getName()+":"+DateUtils.GetDateTimeNow());
                        try {
                            Thread.sleep(1000);
                        } finally {
                            endGate.countDown();
                            System.out.println("线程endGate.countDown()："+Thread.currentThread().getName()+":"+DateUtils.GetDateTimeNow());
                        }
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                }
            };
            t.start();
        }
        Thread.sleep(1000);
        System.out.println("主线程开锁："+Thread.currentThread().getName()+":"+DateUtils.GetDateTimeNow());
        startGate.countDown();
        System.out.println("主线程等待子线程执行完："+Thread.currentThread().getName()+":"+DateUtils.GetDateTimeNow());
        endGate.await();
        System.out.println("子线程执行完："+Thread.currentThread().getName()+":"+DateUtils.GetDateTimeNow());
    }
}
