package com.concurrency.book.sevenChapter;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * 不支持关闭的生产者消费者日志
 *
 * 使用阻塞队列实现日志记录的功能
 * １．多生产者，单消费者
 * ２．将日志写入到阻塞队列中
 * ３．单独线程读取阻塞队列中的消息，并写将消息写入文件
 *
 * Create by liangxifeng on 19-9-19
 */
public class LogWriter {
    private final BlockingQueue<String> queue; //存储日志的队列
    private final LoggerThread logger; //日志线程

    public LogWriter(Writer writer) {
        this.queue = new LinkedBlockingDeque<String>();
        this.logger = new LoggerThread((PrintWriter) writer);
    }

    //开启消费日志线程
    public void start() {
        logger.start();
    }

    //将日志消息写入队列
    public void log(String msg) throws InterruptedException {
        queue.put(msg);
        System.out.println("queue = "+queue.toString());
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
                    String logMsg = queue.take();
                    writer.println(logMsg);
                    System.out.println("有日志写入："+logMsg);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //实例日志文件aaa.txt
        Writer writer = new PrintWriter("/home/lxf/aaa.txt");
        LogWriter logWriter = new LogWriter(writer);
        //开启消费者线程消费队列中的消息
        logWriter.start();
        //多个生产者写日志
        logWriter.log("aaa");
        logWriter.log("bbb");
        logWriter.log("ccc");
        logWriter.log("ddd");
        //关闭文件句柄
        writer.close();
    }
}
