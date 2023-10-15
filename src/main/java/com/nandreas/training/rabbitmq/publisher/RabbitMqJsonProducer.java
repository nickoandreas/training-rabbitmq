package com.nandreas.training.rabbitmq.publisher;

import com.nandreas.training.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonProducer
{
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.json.routing-key.name}")
    private String jsonRoutingKey;

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendJsonMessage(User user)
    {
        LOGGER.info(String.format("Json Message Sent -> %s", user.toString()));

        this.rabbitTemplate.convertAndSend(this.exchangeName, this.jsonRoutingKey, user);
    }
}
