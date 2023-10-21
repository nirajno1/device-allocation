package com.device.allocation.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.device.allocation.config.RabbitMQConfig.QUEUE_NAME;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final RabbitTemplate rabbitTemplate;

    public NotificationServiceImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(QUEUE_NAME, message);
    }
}
