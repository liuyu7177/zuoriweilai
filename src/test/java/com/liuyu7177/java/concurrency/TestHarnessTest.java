package com.liuyu7177.java.concurrency;

import org.junit.Test;

import java.util.Date;

/**
 * Created by liuyu7177 On 2019/6/11
 */
public class TestHarnessTest {

    @Test
    public void timeTasks() throws InterruptedException {
        TestHarness th = new TestHarness();
        TestRunnableImpl tr = new TestRunnableImpl();
        long longTime = th.timeTasks(50, tr);
        Date date = new Date(longTime);
        System.out.println(longTime/1000);
        System.out.println(date.toString());
    }
}