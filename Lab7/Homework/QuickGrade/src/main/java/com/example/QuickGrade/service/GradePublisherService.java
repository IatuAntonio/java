package com.example.QuickGrade.service;


import com.example.QuickGrade.config.RabbitMQConfig;
import com.example.QuickGrade.dto.GradeEventDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class GradePublisherService {

    private final RabbitTemplate rabbitTemplate;

    public GradePublisherService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void publishGradeEvent(GradeEventDTO event) {
        System.out.println("Publish message to queue: " + RabbitMQConfig.GRADE_QUEUE);
        rabbitTemplate.convertAndSend(RabbitMQConfig.GRADE_QUEUE, event);
    }

}
