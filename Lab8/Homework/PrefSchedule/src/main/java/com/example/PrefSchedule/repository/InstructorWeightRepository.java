package com.example.PrefSchedule.repository;

import com.example.PrefSchedule.model.Course;
import com.example.PrefSchedule.model.InstructorWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InstructorWeightRepository extends JpaRepository<InstructorWeight, Long> {

    List<InstructorWeight> findByOptionalCourseId(Long courseId);

}
