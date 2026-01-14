package com.example.PrefSchedule.consumer;

import com.example.PrefSchedule.config.RabbitMQConfig;
import com.example.PrefSchedule.dto.GradeEventDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class GradeConsumer {

    @RabbitListener(queues = RabbitMQConfig.GRADE_QUEUE)
    public void consumeMessage(GradeEventDTO gradeEvent) {
        System.out.println("--- RECEIVED GRADE EVENT ---");
        System.out.println("Student: " + gradeEvent.getStudentCode());
        System.out.println("Course: " + gradeEvent.getCourseCode());
        System.out.println("Grade: " + gradeEvent.getGrade());
        System.out.println("----------------------------");
    }

}
