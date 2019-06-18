package com.liuyu7177.java.concurrency.built;

/**
 * Created by liuyu7177 On 2019/6/18
 */
public interface Computable<A, V> {
    V compute(A arg) throws Exception;
}
