package com.example.Compulsory.repository;

import com.example.Compulsory.model.Course;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Course c WHERE c.instructor.name = :name")
    List<Course> findCoursesByInstructorName(String name);

    List<Course> findByType(String type);

    @Transactional
    @Modifying
    @Query("UPDATE Course c SET c.groupCount = :groupCount WHERE c.id = :id")
    void updateGroupCount(Long id, int groupCount);

}
