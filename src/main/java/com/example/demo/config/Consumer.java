package com.example.demo.config;

import com.rabbitmq.client.*;

import java.io.IOException;

public class Consumer {
    public static  String queueName="tests";
    public static void main(String[] args) throws Exception {
        Connection connection=ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();

        channel.queueDeclare(queueName,false, false, false, null);

        DefaultConsumer consumer=new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                super.handleDelivery(consumerTag, envelope, properties, bb ody);
                System.out.println(new String(body));
            }
        };

        //第二个参数表示是否自动ack，默认自动ack
        channel.basicConsume(queueName,true,consumer);




    }
}
