package com.yhz.moudle.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 服务端线程处理任务
 *
 * @author yanghz
 * @create 2018-11-23 22:22
 **/
public class ServerHandler implements Runnable {
    private Socket socket;
    public ServerHandler(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        BufferedReader in=null;
        PrintWriter out=null;
        try {
            in=new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            out=new PrintWriter(this.socket.getOutputStream(),true);
            String body=null;
            while(true){
                body=in.readLine();
                if(body==null){
                    break;
                }
                System.out.println("服务端接收到的消息："+body);
                out.println("响应客户端信息：你好客户端！");
            }

        }catch(Exception e){

        }finally{
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
