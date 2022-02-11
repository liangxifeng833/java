package com.lxf.rocketdemo.order;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * 订单消息发送者，发送顺序消息
 * 局部顺序，也就是向broker中的某一个队列中发送顺序消息，保证某一个队列有序
 * 消费的时候，采用一个线程消费该顺序队列消息即可
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer,并制定生产者组名
        DefaultMQProducer producer = new DefaultMQProducer("group1");

        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.2.103:9876;192.168.2.103:9877;");
        //不开启vip通道 开通口端口会减2
        producer.setVipChannelEnabled(false);
        //3.启动producer
        producer.start();
        //构建消息集合
        List<OrderStep> orderList = OrderStep.buildOrders();
        System.out.println(orderList);

        //发送消息
        for (int i = 0; i < orderList.size(); i++) {
            String body = orderList.get(i)+"";
            Message msg = new Message("OrderTopic","Order","i"+i,body.getBytes());
            /**
             * 向指定队列中发送消息
             * 参数一： 消息对象
             * 参数二： 消息对象选择器
             * 参数三： 选择队列的业务标识（这里是订单主键id）
             */
            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                /**
                 *
                 * @param list 队列集合
                 * @param message 消息对象
                 * @param arg 业务标示的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                    Integer orderId = (Integer) arg;
                    Integer index = orderId % list.size();
                    return list.get(index);
                }
            }, orderList.get(i).getId());
            System.out.println("发送结果="+sendResult);
        }
        producer.shutdown();

    }
}
