package com.liuyu7177.zuoriweilai;

/**
 * Created by liuyu7177 On 2019/5/31
 */
public class NoVisibility {
    private static boolean ready;
    private static int number;

    private static class ReaderThread extends Thread {
        public void run() {


        }
    }
}
