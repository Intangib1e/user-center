package com.llj.usercenter.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author 刘露杰
 * @Date 2023/4/3 16:12
 * @Description:
 * @Version 1.0
 */
@Configuration
public class TopicRabbitConfig {

    //绑定键
    public final static String queue1 = "queue1";
    public final static String queue2 = "queue2";

    /**
     * 队列man
     * @return 队列
     */
    @Bean
    public Queue firstQueue() {
        return new Queue(queue1);
    }

    /**
     * 队列woman
     * @return 队列
     */
    @Bean
    public Queue secondQueue() {
        return new Queue(queue2);
    }

    /**
     * 交换机：topicExchange
     * @return 交换机
     */
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange("topicExchange");
    }


    //将firstQueue和topicExchange绑定,
    //这样只要是消息携带的路由键是position,才会分发到该队列
    @Bean
    public Binding bindingExchangeMessage() {
        return BindingBuilder.bind(firstQueue()).to(exchange()).with("position");
    }

    //将secondQueue和topicExchange绑定,而且绑定的键值为用上通配路由键规则topic.#
    // 这样只要是消息携带的路由键是以topic.开头,都会分发到该队列
    @Bean
    public Binding bindingExchangeMessage2() {
        return BindingBuilder.bind(secondQueue()).to(exchange()).with("topic.#");
    }

}
