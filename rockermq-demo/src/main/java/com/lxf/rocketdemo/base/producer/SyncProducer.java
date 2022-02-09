package com.lxf.rocketdemo.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 发送同步消息
 */
public class    SyncProducer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.2.103:9876;192.168.2.103:9877;");
        producer.setVipChannelEnabled(false);
        //3.启动producer
        producer.start();
        //4.创建消息对象，指定主题Topic, Tag 和消息体
        for (int i = 0; i < 10; i++) {
            /**
             * 参数说明
             * 参数一：topic=base
             * 参数二：tag=Tag1
             * 参数三: 消息体
             */
            Message msg = new Message("base","Tag1",("Hello world"+i).getBytes());
            //5.同步方式发送消息
            SendResult result = producer.send(msg);
            SendStatus status = result.getSendStatus(); //发送状态
            String msgId = result.getMsgId(); //消息id
            Integer queueId = result.getMessageQueue().getQueueId(); //消息接收队列ID
            System.out.println("发送结果："+result);

            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //6.关闭生产者producer
        producer.shutdown();

    }
}
