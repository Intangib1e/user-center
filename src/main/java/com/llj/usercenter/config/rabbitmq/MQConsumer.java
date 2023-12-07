package com.llj.usercenter.config.rabbitmq;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * @Author 刘露杰
 * @Date 2023/4/3 15:38
 * @Description: 用来模拟消费者消费消息
 * @Version 1.0
 */
@Slf4j
@Component
public class MQConsumer {


    @RabbitListener(queues = "queue1")
    public void  consumerTopic1(JSONObject msg){
        log.info("消费者1监听到【TestDirectQueue】队列的消息：{}",msg);
    }

    @RabbitListener(queues = "queue2")
    public void  consumerTopic2(JSONObject msg){
        log.info("消费者2监听到【TestDirectQueue】队列的消息：{}",msg);
    }

}
