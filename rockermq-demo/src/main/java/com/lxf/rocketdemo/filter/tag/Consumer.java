package com.lxf.rocketdemo.filter.tag;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 以Tag方式，消费者过滤消费的消息
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        //1.创建消费者Consumer, 指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group1");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.1.129:9876;192.168.1.129:9877;");
        //3.订阅主题Topic和Tag
        consumer.subscribe("TagTopic", "Tag1 || Tag2");
        consumer.setVipChannelEnabled(false);
        //消费模式分为：广播和负载均衡模式，默认负载均衡模式MessageModel.CLUSTERING，广播模式设置为：MessageModel.BROADCASTING
        consumer.setMessageModel(MessageModel.BROADCASTING);
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
        System.out.println("以Tag方式消费，消费者开启");
    }

}
