package com.liuyu7177.java.concurrency.tool;

import com.liuyu7177.zuoriweilai.framework.utils.DateUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by liuyu7177 On 2019/8/21
 */
public class TestSemaphore {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        final Semaphore semp = new Semaphore(5);
        final CountDownLatch endGate = new CountDownLatch(20);
        for (int index = 0; index < 20; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        semp.acquire();
                        System.out.println("获取到许可的线程：" + Thread.currentThread().getName() + ":" + DateUtils.GetDateTimeNow());
                        //模拟实际业务逻辑
                        Thread.sleep((long) (Math.random() * 10000));
                        // 访问完后，释放
                        semp.release();
                        System.out.println("释放许可的线程：" + Thread.currentThread().getName() + ":" + DateUtils.GetDateTimeNow());
                        endGate.countDown();
                    } catch (InterruptedException e) {
                    }
                }
            };
            exec.execute(run);
        }
        try {
            System.out.println("主线程等待："+ Thread.currentThread().getName() + ":" + DateUtils.GetDateTimeNow());
            endGate.await();
            System.out.println("主线程结束："+ Thread.currentThread().getName() + ":" + DateUtils.GetDateTimeNow());
            exec.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
