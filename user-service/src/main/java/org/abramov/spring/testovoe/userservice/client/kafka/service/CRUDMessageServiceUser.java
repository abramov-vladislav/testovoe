package org.abramov.spring.testovoe.userservice.client.kafka.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.abramov.spring.testovoe.userservice.client.kafka.producer.DeleteProducerUser;
import org.abramov.spring.testovoe.userservice.client.kafka.producer.InsertProducerUser;
import org.abramov.spring.testovoe.userservice.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CRUDMessageServiceUser {

    private final UserService userService;
    private final DeleteProducerUser deleteProducerUser;
    private final InsertProducerUser insertProducerUser;

}
