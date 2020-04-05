package com.concurrency.book.sevenChapter;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 向不支持关闭的LogWrite添加可靠的取消
 *
 * 使用同步的方式在新增阻塞队列消息时判断是否关闭
 * 使用同步方式在消费者消费消息之前判断中断标识是否中断
 * 直到队列中的所有任务执行完在关闭（使用计数器＋自定义中断标识实现）
 * Create by liangxifeng on 19-9-19
 */
public class LogService {
    private final BlockingQueue<String> queue; //存储日志的队列
    private final LoggerThread logger; //日志线程
    private boolean isShutDown; //关闭消费者日志线程标识
    private int reservations; //生产者生产消息的计数器

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingDeque<String>();
        this.logger = new LoggerThread((PrintWriter) writer);
    }

    //开启消费日志线程
    public void start() {
        logger.start();
    }

    //关闭消费者日志线程
    public void stop() {
        synchronized (this) {
            isShutDown = true;
            logger.interrupt();
        }
    }

    //将日志消息写入队列
    public void log(String msg) throws InterruptedException {
        //同步机制检查消费者线程是否已关闭
        synchronized (this) {
            if (isShutDown) { //如果关闭消费者线程，则写入日志抛出异常
                throw new IllegalStateException();
            }
            ++reservations;
        }
        queue.put(msg);
        //System.out.println("queue = "+queue.toString());
    }

    //日志线程类, 读取阻塞队列中的日志信息并输出到文件中
    private class LoggerThread extends Thread {
        private final PrintWriter writer;

        private LoggerThread(PrintWriter writer) {
            this.writer = writer;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    //同步机制检查关闭标识和消息数量
                    //如果已关闭且消息数量=0都处理完了，那么终止该循环
                    synchronized (LogService.this) {
                        if (isShutDown && reservations == 0 && Thread.currentThread().isInterrupted())
                            break;
                    }
                    String logMsg = queue.take();
                    writer.println(logMsg);
                    //消费完成后同步将消息数量递减
                    synchronized (LogService.this) {--reservations;}
                    System.out.println("有日志写入："+logMsg);
                } catch (InterruptedException e) {
                    System.out.println("已经关闭消费线程");
                    Thread.currentThread().interrupt();
                    //e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //实例日志文件aaa.txt
        Writer writer = new PrintWriter("/home/lxf/aaa.txt");
        LogService logService = new LogService(writer);

        //开启消费者线程消费队列中的消息
        logService.start();
        //多个生产者写日志
        for (int i=0; i<1000; i++) {
            logService.log(Integer.toString(i));
        }
        //关闭消费者线程
        new Thread(()->logService.stop()).start();
        //关闭文件句柄
        writer.close();
    }
}
