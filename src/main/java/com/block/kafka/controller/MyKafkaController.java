package com.block.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangrongsong
 * @title: MyKafkaController
 * @projectName springboot-kafka-study
 * @description: TODO
 * @date 2021-10-09 12:14
 */
@RestController
@RequestMapping("/msg")
public class MyKafkaController {

    private static final String TOPIC_NAMe="my_replicated_topic";

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("send")
    public String sendMessage(){
        ListenableFuture key = kafkaTemplate.send(TOPIC_NAMe, 0, "key", "THis is first message from springboot");
        return "send success!";
    }
}
