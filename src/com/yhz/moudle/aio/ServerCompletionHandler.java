package com.yhz.moudle.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * @Auther: yanghz
 * @Date: 2018/11/27 09:49
 * @Description:
 */
public class ServerCompletionHandler implements CompletionHandler<AsynchronousSocketChannel,Server> {
    @Override
    public void completed(AsynchronousSocketChannel asc, Server attachment) {
        //当有下一个客户端接入的时候 直接调用Server的accept方法，这样反复执行下去，保证多个客户端都可以阻塞
        attachment.assc.accept(attachment,this);
        read(asc);
    }

    @Override
    public void failed(Throwable exc, Server attachment) {
        exc.printStackTrace();
    }
    private void read(final AsynchronousSocketChannel asc){
        //读取数据
        ByteBuffer buf=ByteBuffer.allocate(1024);
        asc.read(buf, buf, new CompletionHandler<Integer, ByteBuffer>() {
            @Override
            public void completed(Integer resultSize, ByteBuffer attachment) {
                //进行读取之后，重置标识位
                attachment.flip();
                //获得读取的字节数
                System.out.println("Server -> 收到客户端的数据长度为："+resultSize);
            }

            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                exc.printStackTrace();
            }
        });
    }
}