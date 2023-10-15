package com.nandreas.training.rabbitmq.consumer;

import com.nandreas.training.rabbitmq.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqJsonConsumer
{
    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMqJsonConsumer.class);

    @RabbitListener(queues = {"${rabbitmq.json.queue.name}"})
    public void consume(User user)
    {
        LOGGER.info(String.format("Received json message -> %s", user.toString()));
    }
}
