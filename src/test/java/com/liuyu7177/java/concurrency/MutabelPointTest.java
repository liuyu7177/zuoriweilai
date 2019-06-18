package com.liuyu7177.java.concurrency;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by liuyu7177 On 2019/6/4
 */
public class MutabelPointTest {
    @Test
    public void testMutabelPoint() {
//        MutabelPoint p1=new MutabelPoint(8,9);
//        MutabelPoint p2=new MutabelPoint(p1);
//        System.out.println(p1);
//        System.out.println(p2);
//        System.out.println("------------------");
////        p1.x=10;
////        p1.y=20;
//        System.out.println(p1);
//        System.out.println(p2);


        ConcurrentMap<Integer, Integer> a=new ConcurrentHashMap<Integer, Integer>();
        Integer b=  a.putIfAbsent(1,2);
        Integer c=  a.putIfAbsent(1,2);
    }
}