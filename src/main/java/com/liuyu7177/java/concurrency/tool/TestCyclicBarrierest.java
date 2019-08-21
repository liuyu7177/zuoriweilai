package com.liuyu7177.java.concurrency.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 栅栏
 * Created by liuyu7177 On 2019/8/21
 */
public class TestCyclicBarrierest {
    private  static  int ThreadCount=10;

    public static void main(String[] args) {
        CyclicBarrier b=new CyclicBarrier(ThreadCount, new Runnable() {
            @Override
            public void run() {
                System.out.println("执行CyclicBarrier.Runnable"+Thread.currentThread().getName());
            }
        });
        for (int i=0;i< ThreadCount;i++){
            Thread t=  new Thread(){
                public void run() {
                    System.out.println("线程到达屏障，开始等待"+Thread.currentThread().getName());
                    try {
                        b.await();//线程在这里等待，直到所有线程都到达“屏障”。
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                    System.out.println("线程"+Thread.currentThread().getName()+" 开始后续工作");
                }
            };
            t.start();
        }
    }
}
