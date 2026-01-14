package com.example.PrefSchedule.repository;

import com.example.PrefSchedule.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.instructor.name = :name")
    List<Course> findCoursesByInstructorName(String name);

    Optional<Course> findByCode(String code);

    List<Course> findByType(String type);


    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.groupCount = :groupCount WHERE c.id = :id")
    void updateGroupCount(Long id, int groupCount);

}
