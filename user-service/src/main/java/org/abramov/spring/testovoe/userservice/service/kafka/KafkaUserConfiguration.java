package org.abramov.spring.testovoe.userservice.service.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//public class KafkaUserConfiguration {
//
//    @Bean
//    public NewTopic userTopic() {
//        return new NewTopic("user", 1, (short) 1);
//        /**
//         * партиция - часть топика (для сохранности данных и ускорения работы брокера сообщений)
//         * репликейшн фактор - хранят копии сообщений в потоке
//         */
//    }
//}
