package com.lxf.rocketdemo.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 单向发送消息
 * 应用在：不特别关心发送结果的场景，比如：日志发送
 *
 */
public class OneWayProducer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.2.103:9876;192.168.2.103:9877;");
        producer.setVipChannelEnabled(false);
        //3.启动producer
        producer.start();
        //4.创建消息对象，指定主题Topic, Tag 和消息体
        for (int i = 0; i < 3; i++) {
            /**
             * 参数说明
             * 参数一：topic=base
             * 参数二：tag=Tag1
             * 参数三: 消息体
             */
            Message msg = new Message("base","Tag3",("Hello world 单向消息"+i).getBytes());
            //5. 单向发送消息
            producer.sendOneway(msg);

            //线程睡1秒
            TimeUnit.SECONDS.sleep(3);
        }
        //6.关闭生产者producer
        producer.shutdown();

    }
}
