package com.device.allocation.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

class RabbitMQConfigTest {
    RabbitMQConfig rabbitMQConfig = new RabbitMQConfig();

    @Test
    void testConnectionFactory() {
        ConnectionFactory result = rabbitMQConfig.connectionFactory();
        Assertions.assertNotNull( result);
    }

    @Test
    void testQueue() {
        Queue result = rabbitMQConfig.queue();
        Assertions.assertNotNull( result);
    }

    @Test
    void testRabbitTemplate() {
        RabbitTemplate result = rabbitMQConfig.rabbitTemplate(null);
        Assertions.assertNotNull( result);
    }
}
