package com.lxf.rocketdemo.order;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

import java.util.List;

/**
 * 消费者消费顺序消息
 */
public class Consumer {

    public static void main(String[] args) throws Exception {
        //1.创建消费者Consumer, 指定消费者组名
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("group-new");
        //2.指定Nameserver地址
        consumer.setNamesrvAddr("192.168.2.103:9876;192.168.2.103:9877;");
        //3.订阅主题Topic和Tag
        consumer.subscribe("OrderTopic", "*");
        //TODO 但在顺序消息时，consumer一直不消费消息了，找了好久都没有找到原因，直到我这里也设置为VipChannelEnabled(false)，竟然才可以消费消息。
        consumer.setVipChannelEnabled(false);
        //消费模式分为：广播和负载均衡模式，默认负载均衡模式MessageModel.CLUSTERING，广播模式设置为：MessageModel.BROADCASTING
        //consumer.setMessageModel(MessageModel.CLUSTERING);
        //4.设置回调函数，处理消息
        consumer.registerMessageListener(new MessageListenerOrderly(){
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
                for (MessageExt msg : list) {
                    System.out.println("消费顺序消息，线程名称=【"+ Thread.currentThread().getName() +"】，消息内容：" +new String(msg.getBody()));
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        });

        //启动消费者consumer
        consumer.start();
        System.out.println("顺序消息消费者启动成功");
    }

}
