package com.example.java_rabbitmq.config;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;


public class Producer {

    public static void main(String[] args) throws Exception {
        String queueName="test";
        Connection connection= ConnectionUtil.getConnection();

        Channel channel=connection.createChannel();

        channel.queueDeclare(queueName,false, false, false, null);


        String message = "dddddddd!";
        channel.basicPublish("", queueName, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();



    }

}
