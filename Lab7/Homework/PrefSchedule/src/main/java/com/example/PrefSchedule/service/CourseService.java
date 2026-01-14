package com.example.PrefSchedule.service;

import com.example.PrefSchedule.model.Course;
import com.example.PrefSchedule.repository.CourseRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> findByType(String type) {
        return courseRepository.findByType(type);
    }

    public List<Course> findByInstructorName(String name) {
        return courseRepository.findCoursesByInstructorName(name);
    }

    public Course saveCourse(Course c) {
        return courseRepository.save(c);
    }

    @Transactional
    public void updateGroupCount(Long id, int groupCount) {
        courseRepository.updateGroupCount(id, groupCount);
    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

}
