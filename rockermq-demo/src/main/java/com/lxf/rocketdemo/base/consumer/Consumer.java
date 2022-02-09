package com.lxf.rocketdemo.base.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者消费消息
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        //1.创建消费者Consumer, 指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.2.103:9876;192.168.2.103:9877;");
        //3.订阅主题Topic和Tag
        consumer.subscribe("base", "Tag3");
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerConcurrently(){
            //接收消息内容
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                for (MessageExt msg : msgs) {
                    System.out.println("消费者消费消息："+ new String(msg.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        //启动消费者consumer
        consumer.start();
    }

}
