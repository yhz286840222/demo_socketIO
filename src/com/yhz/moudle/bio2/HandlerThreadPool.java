package com.yhz.moudle.bio2;

import java.util.concurrent.*;

/**
 * 自定义线程池
 *
 * @author yanghz
 * @create 2018-11-23 22:50
 **/
public class HandlerThreadPool {
    private ExecutorService executor;
    public HandlerThreadPool(int maxPoolSize, int queueSize) {
        this.executor=new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
                maxPoolSize,
                120L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(queueSize));
    }
    public void execute(Runnable task){
        this.executor.execute(task);
    }
}
