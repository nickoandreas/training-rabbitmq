package com.nandreas.training.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig
{
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    @Value("${rabbitmq.json.queue.name}")
    private String jsonQueueName;

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    @Value("${rabbitmq.routing-key.name}")
    private String routingKey;

    @Value("${rabbitmq.json.routing-key.name}")
    private String jsonRoutingKey;

    @Bean
    public Queue queue()
    {
        return new Queue(this.queueName);
    }

    @Bean
    public Queue jsonQueue()
    {
        return new Queue(this.jsonQueueName);
    }

    @Bean
    public TopicExchange exchange()
    {
        return new TopicExchange(this.exchangeName);
    }

    @Bean
    public Binding binding()
    {
        return BindingBuilder.bind(this.queue())
                .to(this.exchange())
                .with(this.routingKey);
    }

    @Bean
    public Binding jsonBinding()
    {
        return BindingBuilder.bind(this.jsonQueue())
                .to(this.exchange())
                .with(this.jsonRoutingKey);
    }

    @Bean
    public MessageConverter converter()
    {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory)
    {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(this.converter());

        return rabbitTemplate;
    }
}
