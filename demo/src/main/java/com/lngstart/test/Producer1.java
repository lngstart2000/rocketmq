package com.lngstart.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class Producer1 {
    public static void main(String[] args) throws Exception{
        // 实例化消息生产者
        DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
        // 设置namesrv地址
        producer.setNamesrvAddr("192.168.16.128:9876");
        // 设置失败重试次数
        producer.setRetryTimesWhenSendFailed(2);
        // 设置消息发送超时时间
        producer.setSendMsgTimeout(3000);
        // 启动producer实例
        producer.start();
        for(int i = 0; i < 100; ++ i) {
            // 创建消息，并指定topic,tag和消息体
            Message msg = new Message("TopicTest", "TagA", ("hello rocketmq" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            // 发送一个消息到broker
            SendResult sendResult = producer.send(msg);
            System.out.println(sendResult);
        }
        // 如果不再发送消息关闭Producer实例
        producer.shutdown();
    }
}
