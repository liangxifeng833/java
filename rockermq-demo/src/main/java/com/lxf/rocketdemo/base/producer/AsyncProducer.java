package com.lxf.rocketdemo.base.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * 发送异步消息
 * 应用在对响应时间敏感的业务场景，即发送端不能容忍长时间等待Broker的响应
 * 可通过回调函数获得发送消息结果
 */
public class AsyncProducer {
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
            Message msg = new Message("base","Tag2",("Hello world"+i).getBytes());
            //5.异步方式发送消息，通过回调函数获取发的消息结果
            producer.send(msg, new SendCallback() {
                /**
                 * 发送成功回调函数
                 * @param sendResult
                 */
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println("发送成功，发送结果：" + sendResult);
                }

                /**
                 * 发送失败回调函数
                 * @param throwable
                 */
                @Override
                public void onException(Throwable throwable) {
                    System.out.println("发送失败，异常信息:" + throwable);
                }
            });

            //线程睡1秒
            TimeUnit.SECONDS.sleep(1);
        }
        //6.关闭生产者producer
        producer.shutdown();

    }
}
