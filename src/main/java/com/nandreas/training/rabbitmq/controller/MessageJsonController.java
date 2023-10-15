package com.nandreas.training.rabbitmq.controller;

import com.nandreas.training.rabbitmq.dto.User;
import com.nandreas.training.rabbitmq.publisher.RabbitMqJsonProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageJsonController
{
    @Autowired
    private RabbitMqJsonProducer rabbitMqJsonProducer;

    @PostMapping("/publish")
    public ResponseEntity<String> sendJsonMessage(@RequestBody User user)
    {
        this.rabbitMqJsonProducer.sendJsonMessage(user);

        return ResponseEntity.ok().body("Json message sent to rabbitmq...");
    }
}
