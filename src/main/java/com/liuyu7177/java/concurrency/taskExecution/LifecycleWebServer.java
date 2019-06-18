package com.liuyu7177.java.concurrency.taskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

/**
 * Created by liuyu7177 On 2019/6/18
 */
public class LifecycleWebServer {
    private static final int NTHREADS = 100;
    private final ExecutorService exec = Executors.newFixedThreadPool(NTHREADS);

    public void start() throws IOException {
        ServerSocket socket = new ServerSocket(80);
        while (!exec.isShutdown()) {
            try {
                final Socket conn = socket.accept();
                exec.execute(new Runnable() {
                    @Override
                    public void run() {
                        handleRequest(conn);
                    }
                });
            } catch (RejectedExecutionException e) {
                if (!exec.isShutdown()) {
                    System.out.println("task submission rejected" + e.getMessage());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        exec.shutdown();
    }

    private void handleRequest(Socket conn) {
        Request req=readRequest(conn);
        if(isShutdownRequest(req))
        {
            stop();
        }else{
            dispatchRequest(req);
        }
    }

    private void dispatchRequest(Request req) {
    }

    private boolean isShutdownRequest(Request req) {
        return  false;
    }

    private Request readRequest(Socket conn) {
        return  new Request();
    }
}
