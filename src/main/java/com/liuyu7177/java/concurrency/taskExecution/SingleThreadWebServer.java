package com.liuyu7177.java.concurrency.taskExecution;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by liuyu7177 On 2019/6/18
 */
public class SingleThreadWebServer {
    public static void  main(String[] args) throws IOException{
        ServerSocket socket=new ServerSocket(80);
        while (true){
            Socket connection=socket.accept();
            handleRequest(connection);
        }
    }

    private static void handleRequest(Socket connection) {
    }
}
