package com.yhz.moudle.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
            Socket socket=server.accept();
            System.out.println("服务启动！");
            new Thread(new ServerHandler(socket)).start();
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
