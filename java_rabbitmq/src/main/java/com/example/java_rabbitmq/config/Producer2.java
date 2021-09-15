package com.example.java_rabbitmq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * 带生产者确认机制
 */
public class Producer2 {
    public static void main(String[] args) throws Exception {
        //声明队列名称
        String queueName="tests";
        //创建连接
        Connection connection= ConnectionUtil.getConnection();

        //创建通道
        Channel channel=connection.createChannel();

        //创建队列
        channel.queueDeclare(queueName,false, false, false, null);

        //开启生产者确认机制
        channel.confirmSelect();

        //添加确认监听，判断消息是否正常到达交换机
        channel.addConfirmListener(
                (deliveryTag,multiple)->{
                    System.out.println("成功发送队列");
                    System.out.println(deliveryTag+"."+multiple);
                }
        , (deliveryTag,multiple)->System.out.println("失败发送队列"));



        String message = "dddddddd!";
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();



    }
}
