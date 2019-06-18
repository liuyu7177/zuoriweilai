package com.liuyu7177.java.concurrency;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * Created by liuyu7177 On 2019/6/11
 * CountDownLatch 闭锁测试类
 */
public class TestHarness {
    public long timeTasks(int nThreads, final Runnable task) throws InterruptedException {
        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);
        for (int i = 0; i < nThreads; i++) {
            Thread t = new Thread() {
                public void run() {
                    try {
                        startGate.await();
                        try {
                            task.run();
                        } finally {
                            endGate.countDown();
                        }
                    } catch (InterruptedException ignored) {
                    }
                }
            };
            t.start();
        }
        long start = System.nanoTime();
        Instant inst1 = Instant.now();
        startGate.countDown();
        endGate.await();
        Instant inst2 = Instant.now();
        long end = System.nanoTime();
        System.out.println("以毫秒计的时间差：" + Duration.between(inst1, inst2).toMillis());
        System.out.println("以秒计的时间差：" + Duration.between(inst1, inst2).getSeconds());

       return end - start;
    }
}
