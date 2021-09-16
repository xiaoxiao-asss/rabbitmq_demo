package com.example.springboot_rabbitmq.controller;

import com.example.springboot_rabbitmq.entity.Demo;
import com.example.springboot_rabbitmq.entity.User;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class TestController {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg")
    public String sendMsg(){
        for (int i = 1; i <= 10; i++) {
            if(i%2==0){
                rabbitTemplate.convertAndSend("hello_exchange","test",new User(i,"的代价"),new CorrelationData(UUID.randomUUID().toString()));
            }else{
                rabbitTemplate.convertAndSend("hello_exchange","test",new Demo(i,"demo............."),new CorrelationData(UUID.randomUUID().toString()));
                //测试将消息投递到交换机没有绑定的路由键，触发ReturnsCallback失败回调
                //  rabbitTemplate.convertAndSend("hello_exchange","wws",new Demo(i,"demo............."),new CorrelationData(UUID.randomUUID().toString()));
            }

        }
        return "msg";
    }


    @GetMapping("/sendMsg2")
    public String sendMsg2(){
        for (int i = 1; i <= 10; i++) {

                rabbitTemplate.convertAndSend("hello_exchange","test",new User(i,"的代价"),new CorrelationData(UUID.randomUUID().toString()));


        }
        return "msg";
    }
}
