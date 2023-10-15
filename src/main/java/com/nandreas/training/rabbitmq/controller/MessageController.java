package com.nandreas.training.rabbitmq.controller;

import com.nandreas.training.rabbitmq.publisher.RabbitMqProducer;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MessageController
{
    @Autowired
    private RabbitMqProducer producer;

    @GetMapping("/publish")
    public ResponseEntity<String> sendMessage(@RequestParam(name = "message") String message)
    {
        this.producer.sendMessage(message);

        return ResponseEntity.ok().body("Message sent to RabbitMq...");
    }
}
