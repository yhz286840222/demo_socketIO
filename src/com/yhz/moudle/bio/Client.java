package com.yhz.moudle.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 客户端
 *
 * @author yanghz
 * @create 2018-11-23 22:21
 **/
public class Client {
    private static final String HOST="127.0.0.1";
    private static final int PORT=8888;

    public static void main(String[] args) {
        BufferedReader in=null;
        PrintWriter out=null;
        Socket socket=null;
        try{
            socket=new Socket(HOST,PORT);
            in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream(),true);
            out.println("你好服务器！");
            String body=in.readLine();
            System.out.println(body);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                if(in!=null){
                    in.close();
                }
                if(out!=null){
                    out.close();
                }
                if(socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
