package com.yhz.moudle.aio;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: yanghz
 * @Date: 2018/11/27 09:48
 * @Description:
 */
public class Server {
    //线程池
    private ExecutorService executorService;
    //线程组
    private AsynchronousChannelGroup threadGroup;
    //服务器通道
    public AsynchronousServerSocketChannel assc;

    public Server(int port){
        try{
            //1.创建一个缓存池
            executorService=Executors.newCachedThreadPool();
            //2.创建线程组
            threadGroup=AsynchronousChannelGroup.withCachedThreadPool(executorService,1);
            //3.创建服务通道
            assc=AsynchronousServerSocketChannel.open(threadGroup);
            //4.绑定端口
            assc.bind(new InetSocketAddress(port));
            System.out.println("server start port:"+port);
            //5.进行阻塞
            assc.accept(this,new ServerCompletionHandler());
            //6.一直阻塞，不让服务器停止
            Thread.sleep(Integer.MAX_VALUE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

    }
}
