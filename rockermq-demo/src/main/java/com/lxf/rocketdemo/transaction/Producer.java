package com.lxf.rocketdemo.transaction;

import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.tomcat.util.codec.binary.StringUtils;

import javax.xml.stream.Location;
import java.util.concurrent.TimeUnit;

/**
 * 事务消息producer
 */
public class Producer {
    public static void main(String[] args) throws Exception {
        //1.创建消息生产者producer,并制定生产者组名
        TransactionMQProducer producer = new TransactionMQProducer("group5");

        //2.指定Nameserver地址
        producer.setNamesrvAddr("192.168.1.129:9876;192.168.1.129:9877;");
        producer.setVipChannelEnabled(false);
        //添加事务监听器
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 在该方法中执行本地事务
             * @param message
             * @param o
             * @return
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message message, Object o) {
                if("TAGA".equals(message.getTags())) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if ( "TAGB".equals(message.getTags()) ) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                }else if( "TAGB".equals(message.getTags()) ) {
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.UNKNOW;
            }

            /**
             * 该方法是MQ进行消息状态的回差
             * @param messageExt
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt messageExt) {
                System.out.println("消息的Tag："+messageExt.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        //3.启动producer
        producer.start();
        String[] tags = {"TAGA","TAGB","TAGC"};
        //4.创建消息对象，指定主题Topic, Tag 和消息体
        for (int i = 0; i < 3; i++) {
            /**
             * 参数说明
             * 参数一：topic=base
             * 参数二：tag=Tag1
             * 参数三: 消息体
             */
            Message msg = new Message("TransactionTopic",tags[i],("Hello world"+i).getBytes());
            //5.同步方式发送事务消息
            SendResult result = producer.sendMessageInTransaction(msg, null); //第二个参数为null，代表对所有发出去的消息都进行事务控制,可以设定为对某一个事务消息进行事务控制
            SendStatus status = result.getSendStatus(); //发送状态
            String msgId = result.getMsgId(); //消息id
            Integer queueId = result.getMessageQueue().getQueueId(); //消息接收队列ID
            System.out.println("发送结果："+result);

            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //6.关闭生产者producer
        //producer.shutdown();

    }
}
