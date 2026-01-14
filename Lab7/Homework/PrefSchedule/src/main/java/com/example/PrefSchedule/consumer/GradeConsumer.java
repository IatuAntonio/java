package com.example.PrefSchedule.consumer;

import com.example.PrefSchedule.config.RabbitMQConfig;
import com.example.PrefSchedule.dto.GradeEventDTO;
import com.example.PrefSchedule.model.Course;
import com.example.PrefSchedule.model.Grade;
import com.example.PrefSchedule.repository.CourseRepository;
import com.example.PrefSchedule.repository.GradeRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GradeConsumer {

    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;

    public GradeConsumer(GradeRepository gradeRepository, CourseRepository courseRepository) {
        this.gradeRepository = gradeRepository;
        this.courseRepository = courseRepository;
    }

    @RabbitListener(queues = RabbitMQConfig.GRADE_QUEUE)
    public void consumeMessage(GradeEventDTO gradeEvent) {
        System.out.println("--- Processing grade event: " + gradeEvent + " ---");

        if (gradeEvent.getGrade() < 0) {
            throw new RuntimeException("Grade must be greater than 0");
        }

        Optional<Course> courseOpt = courseRepository.findByCode(gradeEvent.getCourseCode());

        if (courseOpt.isPresent()) {
            Course course = courseOpt.get();
            if ("compulsory".equalsIgnoreCase(course.getType())) {
                Grade grade = new Grade(
                        gradeEvent.getStudentCode(),
                        gradeEvent.getCourseCode(),
                        gradeEvent.getGrade()
                );
                gradeRepository.save(grade);
                System.out.println("Saved grade: " + grade);
            } else {
                System.out.println("Course " + course.getCode() + " is not compulsory. Grade not saved.");
            }
        } else {
            System.out.println("Course with code " + gradeEvent.getCourseCode() + " not found.");
        }
    }

}
