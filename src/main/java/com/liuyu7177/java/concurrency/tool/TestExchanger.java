package com.liuyu7177.java.concurrency.tool;

import java.util.concurrent.Exchanger;

/**
 * Created by liuyu7177 On 2019/8/21
 */
public class TestExchanger {
    public static void main(String[] args) {
         Exchanger<String> exchange=new Exchanger<String>();
         for (int i=0;i<2;i++){
             Thread t = new Thread() {
                 public void run() {
                     try {
                        String str="当前线程名："+Thread.currentThread().getName()+":";
                         str += exchange.exchange(str);
                         System.out.println(str);
                     } catch (Exception e) {
                         System.out.println(e.toString());
                     }
                 }
             };
             t.start();
         }
    }
}
