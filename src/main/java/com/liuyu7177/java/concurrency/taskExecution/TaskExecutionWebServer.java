package com.liuyu7177.java.concurrency.taskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by liuyu7177 On 2019/6/18
 */
public class TaskExecutionWebServer {
    private static final int NTHREADS=100;
    private static final Executor exec= Executors.newFixedThreadPool(NTHREADS);
    public static void main(String[] args) throws IOException{
        ServerSocket socket=new ServerSocket(80);
        while (true){
            final Socket connection=socket.accept();
            Runnable task=new Runnable() {
                @Override
                public void run() {
                    handleRequest(connection);
                }
            };
            exec.execute(task);
        }
    }

    private static void handleRequest(Socket connection) {
    }
}
