package com.liuyu7177.java.concurrency;

/**
 * Created by liuyu7177 On 2019/6/11
 */
public class TestRunnableImpl implements Runnable {
    @Override
    public void run() {
        //啥也不干
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
