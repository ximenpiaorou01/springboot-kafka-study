package com.block.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

/**
 * @author wangrongsong
 * @title: MyConsumer
 * @projectName springboot-kafka-study
 * @description: TODO
 * @date 2021-10-09 12:21
 */
@Component
public class MyConsumer {

    /**
     * 拿到一条消费记录
     * @param record
     * @param ack
     */
//    @KafkaListener(topics = "my_replicated_topic",groupId="MyGroup1")
//    public void listGroup(ConsumerRecord<String,String> record, Acknowledgment ack){
//        String value = record.value();
//        System.out.println(value);
//        System.out.println(record);
//        //手动提交offset
//        ack.acknowledge();
//    }

    @KafkaListener(groupId="testGroup",topicPartitions = {
       @TopicPartition(topic = "topic1",partitions = {"0","1"}),//指定topic1从partition=0或者1开始消费
       @TopicPartition(topic = "topic2",partitions = "0",//指定topic2的0号分区partition从最近offset开始消费
       partitionOffsets = @PartitionOffset(partition = "1",initialOffset = "100"))//指定topic2的1号分区从offset=100位置开始消费
    },concurrency = "3")//concurrency就是同组下的消费者个数，就是并发消费数，建议小于等于分区partition总数
    public void listGroup(ConsumerRecord<String,String> record, Acknowledgment ack){
        String value = record.value();
        System.out.println(value);
        System.out.println(record);
        //手动提交offset
        ack.acknowledge();
    }



    /**
     * 拿到一批消费记录
     * @param records
     * @param ack
     */
    @KafkaListener(topics = "my_replicated_topic",groupId="MyGroup1")
    public void listGroup_1(ConsumerRecords<String,String> records, Acknowledgment ack){

        records.forEach(record ->{
            String value = record.value();
            System.out.println(value);
            System.out.println(record);
        } );

        //手动提交offset
        ack.acknowledge();
    }

}
