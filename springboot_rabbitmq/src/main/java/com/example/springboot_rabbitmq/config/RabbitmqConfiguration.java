package com.example.springboot_rabbitmq.config;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;


@Configuration
public class RabbitmqConfiguration {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }



   @PostConstruct
    public RabbitTemplate rabbitTemplate(){
       /**
        * 消息到达broker的回调
        * correlationData 消息的唯一id
        * ack 消息是否成功接收到
        * cause 失败原因
        */
       rabbitTemplate.setConfirmCallback((correlationData,ack,cause)->{
           System.out.println(correlationData);
        });


       /**
        * 消息失败到达队列回调
        * returned
        */
       rabbitTemplate.setReturnsCallback((ReturnedMessage returned)->{
           //ReturnedMessage [message=(Body:'{"id":9,"name":"demo............."}' MessageProperties [headers={spring_returned_message_correlation=667a7df6-93a2-496a-88f2-fcdeafd41f24, __TypeId__=com.example.springboot_rabbitmq.entity.Demo}, contentType=application/json, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, deliveryTag=0]), replyCode=312, replyText=NO_ROUTE, exchange=hello_exchange, routingKey=wws]
           System.out.println(returned);
       });

        return rabbitTemplate;
    }
}
