package com.example.springboot_rabbitmq;

import com.example.springboot_rabbitmq.entity.Demo;
import com.example.springboot_rabbitmq.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
@Slf4j
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage3(){
        for (int i = 1; i <= 10; i++) {
            if(i%2==0){
                rabbitTemplate.convertAndSend("hello_exchange","test",new User(i,"的代价"),new CorrelationData(UUID.randomUUID().toString()));
            }else{
                 rabbitTemplate.convertAndSend("hello_exchange","test",new Demo(i,"demo............."),new CorrelationData(UUID.randomUUID().toString()));
                 //测试将消息投递到交换机没有绑定的路由键，触发ReturnsCallback失败回调
                //  rabbitTemplate.convertAndSend("hello_exchange","wws",new Demo(i,"demo............."),new CorrelationData(UUID.randomUUID().toString()));
            }

        }

    }

    @Test
    public void testSendMessage2(){
        for (int i = 1; i <= 10; i++) {
            if(i%2==0){
                rabbitTemplate.convertAndSend("hello_exchange","test",new User(i,"的代价"));
            }else{
                rabbitTemplate.convertAndSend("hello_exchange","test",new Demo(i,"demo............."));
            }

        }

    }


    @Test
    public void testSendMessage(){
        rabbitTemplate.convertAndSend("hello_exchange","test","dddddddddd");
    }


    @Test
    //String name, boolean durable, boolean autoDelete, Map<String, Object> arguments
    public void testCreateExchange(){
        DirectExchange directExchange=  new DirectExchange("hello_exchange",true,false);
        amqpAdmin.declareExchange(directExchange);
        log.info("exchange [{}]创建成功","hello_exchange");
    }


    @Test
    //String destination, Binding.DestinationType destinationType, String exchange, String routingKey, @Nullable Map<String, Object> arguments
    public void testCreateBind(){
        Binding binding=new Binding("test_queue",Binding.DestinationType.QUEUE,"hello_exchange","test",null);
        amqpAdmin.declareBinding(binding);
        log.info("binding 创建成功");
    }

    @Test
    //String name, boolean durable, boolean exclusive, boolean autoDelete, @Nullable Map<String, Object> arguments
    public void testCreateQueue(){
        Queue queue=new Queue("test_queue",true,false,false);
        amqpAdmin.declareQueue(queue);
        log.info("queue [{}]创建成功","test_queue");
    }
}
