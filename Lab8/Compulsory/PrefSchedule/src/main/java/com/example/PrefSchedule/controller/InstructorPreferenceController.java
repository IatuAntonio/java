package com.example.PrefSchedule.controller;

import com.example.PrefSchedule.model.Course;
import com.example.PrefSchedule.model.InstructorWeight;
import com.example.PrefSchedule.repository.CourseRepository;
import com.example.PrefSchedule.repository.InstructorWeightRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/instructor/preferences")
public class InstructorPreferenceController {

    private final InstructorWeightRepository weightRepo;
    private final CourseRepository courseRepo;

    public InstructorPreferenceController(InstructorWeightRepository weightRepo, CourseRepository courseRepo) {
        this.weightRepo = weightRepo;
        this.courseRepo = courseRepo;
    }

    @PostMapping
    public ResponseEntity<?> addPreference(@RequestBody Map<String, Object> payload) {
        Long courseId = Long.valueOf(payload.get("courseId").toString());
        String abbr = (String) payload.get("abbr");
        Double weight = Double.valueOf(payload.get("weight").toString());

        Course course = courseRepo.findById(courseId).orElseThrow();

        InstructorWeight iw = new InstructorWeight(course, abbr, weight);
        weightRepo.save(iw);

        return ResponseEntity.ok("Preference added successfully");
    }

}
