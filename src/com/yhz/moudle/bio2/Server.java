package com.yhz.moudle.bio2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * 服务端
 *传统模式jdk1.5版本之前
 * @author yanghz
 * @create 2018-11-23 22:20
 **/
public class Server {
    private static final int PORT=8888;

    public static void main(String[] args) {
        ServerSocket server=null;
        try {
            server=new ServerSocket(PORT);
            System.out.println("server start！");
            HandlerThreadPool pool= new HandlerThreadPool(50,1000);
            while(true){
                Socket socket=server.accept();
                pool.execute(new ServerHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(server!=null){
                    server.close();
                }
                server=null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
