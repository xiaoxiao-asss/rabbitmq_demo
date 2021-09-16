package com.example.springboot_rabbitmq.service.impl;

import com.example.springboot_rabbitmq.entity.Demo;
import com.example.springboot_rabbitmq.entity.User;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.impl.ChannelN;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
//监听指定队列
@RabbitListener(queues={"test_queue"})
/**
 * RabbitListener 可修饰类，方法
 * RabbitHandler 修饰方法
 */
public class RabbitHandlerDemo {


//    /**
//     * 只处理队列中User类型的消息
//     * @param message
//     * @param user
//     */
//    @RabbitHandler
//    public void getMessage(Message message, User user){
//        System.out.println(user);
    //body消息内容
//        System.out.println(message.getBody());
//    }
//
    /**
     * 只处理队列中Demo类型的消息
     * @param demo
     */
    @RabbitHandler
    public void getMessage(Message message,Demo demo,Channel channel) throws IOException {
        long deliveryTag= message.getMessageProperties().getDeliveryTag();
        System.out.println(demo);

        channel.basicAck(deliveryTag,false);
    }


    /**
     * 只处理队列中User类型的消息
     * @param message
     * @param user
     * @param channel 用来手动确认
     */
    @RabbitHandler
    public void getMessage(Message message, User user, Channel channel) throws IOException {
        long deliveryTag= message.getMessageProperties().getDeliveryTag();
        System.out.println(deliveryTag);
        System.out.println(user);
        if(deliveryTag%2==0){
            try {
                //可能由于网络问题造成数据消费，但是无法回复队列
                /**
                 * long deliveryTag, boolean multiple
                 * deliveryTag
                 * multiple 是否批量确认
                 */
                channel.basicAck(deliveryTag,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            //不确认机制有basicNack ， basicReject两种 ,basicReject机制没有批量处理 ，二选一
            /**
             * long deliveryTag, boolean multiple, boolean requeue
             * deliveryTag
             * multiple 是否批量不确认
             * requeue 不确认的消息是否重新放回队列处理,  false为不处理丢掉
             */
            channel.basicNack(deliveryTag,false,true);

            /**
             * long deliveryTag, boolean multiple, boolean requeue
             * deliveryTag
             * requeue 不确认的消息是否重新放回队列处理,  false为不处理丢掉
             */
          //  channel.basicReject(deliveryTag,true);
        }


    }
}
