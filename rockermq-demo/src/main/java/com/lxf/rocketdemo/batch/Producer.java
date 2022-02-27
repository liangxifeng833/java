package com.lxf.rocketdemo.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 同步发送批量消息
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group2");

        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.1.129:9876;192.168.1.129:9877;");
        producer.setVipChannelEnabled(false);
        //3.启动producer
        producer.start();
        //4.创建消息对象，指定主题Topic, Tag 和消息体
        /**
         * 参数说明
         * 参数一：topic=base
         * 参数二：tag=Tag1
         * 参数三: 消息体
         */
        Message msg1 = new Message("BatchTopic","Tag1",("Hello world"+1).getBytes());
        Message msg2 = new Message("BatchTopic","Tag1",("Hello world"+2).getBytes());
        Message msg3 = new Message("BatchTopic","Tag1",("Hello world"+3).getBytes());
        List<Message> msgs = new ArrayList<>();
        msgs.add(msg1);
        msgs.add(msg2);
        msgs.add(msg3);

        //5.同步方式发送消息
        SendResult result = producer.send(msgs);
        SendStatus status = result.getSendStatus(); //发送状态
        String msgId = result.getMsgId(); //消息id
        Integer queueId = result.getMessageQueue().getQueueId(); //消息接收队列ID
        System.out.println("发送结果："+result);
        //线程睡1秒
        TimeUnit.SECONDS.sleep(1);

        //6.关闭生产者producer
        producer.shutdown();

    }
}
