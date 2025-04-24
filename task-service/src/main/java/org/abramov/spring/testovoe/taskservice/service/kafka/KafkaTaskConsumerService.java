package org.abramov.spring.testovoe.taskservice.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
//public class KafkaTaskConsumerService {
//    /**
//     * принимает сообщения
//     * пометить аннотацией метод, который принимает топики @KafkaListener и указать топик откуда принимает
//     */
//
//    @KafkaListener(topics = "user", groupId = "my_user")
//    public void listen(ConsumerRecord<String, String> record) {
//        System.out.println(record.value());
//    }
//
//}
