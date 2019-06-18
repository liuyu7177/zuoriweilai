package com.liuyu7177.java.concurrency;

import com.liuyu7177.zuoriweilai.model.entity.UserInfo;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.FutureTask;

/**
 * Created by liuyu7177 On 2019/6/11
 */
public class Preloader {
    private final FutureTask<UserInfo> future = new FutureTask<UserInfo>(new Callable<UserInfo>() {
        @Override
        public UserInfo call() throws Exception {
            return new UserInfo(1, "liuyu7177", "note");
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public UserInfo get() {
        try {
            return future.get();
        } catch (Exception e) {
            Throwable cause=e.getCause();
           /* if(cause instanceof  DataLoadException){

            }
            e.printStackTrace();*/
           throw launderThrowable(cause);

        }
    }
    public  static RuntimeException launderThrowable(Throwable t){
        return (RuntimeException)t;
    }
}
