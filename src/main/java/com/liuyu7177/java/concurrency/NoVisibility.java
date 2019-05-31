package com.liuyu7177.java.concurrency;

/**
 * 不可见的
 * Created by liuyu7177 On 2019/5/31
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {
            while (!ready) {
                Thread.yield();
                System.out.println(number);
            }
            System.out.println("--"+number);
        }
    }

    /**
     * 多执行几次，每次的结果都可能不一样
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(2);
        number=42; ready=true;
    }
}
