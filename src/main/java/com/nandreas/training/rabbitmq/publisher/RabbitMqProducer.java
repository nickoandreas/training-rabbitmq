package com.nandreas.training.rabbitmq.publisher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqProducer
{
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key.name}")
    private String routingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message)
    {
        LOGGER.info(String.format("Message sent -> %s", message));

        this.rabbitTemplate.convertAndSend(this.exchangeName, this.routingKey, message);
    }
}
