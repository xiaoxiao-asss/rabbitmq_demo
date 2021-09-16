package com.example.springboot_rabbitmq.service.impl;

import com.example.springboot_rabbitmq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerMessageImpl {

//    @RabbitListener(queues={"test_queue"})
//    public void getMessage(Object object){
//        log.error("object:{},type:{}",object,object.getClass());
//    }

    /**
     * message rabbitmq message内容   user类型的消息
     */
//    @RabbitListener(queues={"test_queue"})
//    public void getMessage(Message message, User user){
    //body消息内容
//        System.out.println(message.getBody());
    //MessageProperties 消息头信息
//        System.out.println(message.getMessageProperties());
//    }
}
