package com.llj.usercenter.controller;

import cn.hutool.json.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.UUID;


/**
 * @Author 刘露杰
 * @Date 2023/4/3 14:46
 * @Description: 用来模拟生产者发送消息
 * @Version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/admin/send")
public class SendMQMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/topic1")
    public String sendTopicMessage1() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("messageId", UUID.randomUUID());
        jsonObject.putOnce("messageData", "最美的不是下雨天");
        jsonObject.putOnce("locationTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend("topicExchange", "position", jsonObject);
        return "发送成功";
    }

    @GetMapping("/topic2")
    public String sendTopicMessage2() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOnce("messageId", UUID.randomUUID());
        jsonObject.putOnce("messageData", "而是和你躲过雨的屋檐");
        jsonObject.putOnce("locationTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        rabbitTemplate.convertAndSend("topicExchange", "topic.123", jsonObject);
        return "发送成功";
    }

}
