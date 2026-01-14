package com.example.PrefSchedule.controller;


import com.example.PrefSchedule.dto.CourseCapacity;
import com.example.PrefSchedule.dto.StudentCandidate;
import com.example.PrefSchedule.model.Pack;
import com.example.PrefSchedule.service.MatchingIntegrationService;
import com.example.PrefSchedule.service.PackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.PrefSchedule.dto.MatchingRequestDTO;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    private final PackService packService;
    private final MatchingIntegrationService matchingService;

    public PackController(PackService packService, MatchingIntegrationService matchingService) {
        this.matchingService = matchingService;
        this.packService = packService;
    }

    @PostMapping
    public ResponseEntity<Pack> create(@RequestBody Pack payload) {
        Pack saved = packService.savePack(payload);
        return ResponseEntity.created(URI.create("/api/packs/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Pack> findAll() {
        return packService.findAllPacks();
    }

    @GetMapping("/{id}")
    public Pack findOne(@PathVariable Long id) {
        return packService.findAllPacks().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }


    @PostMapping("/{packId}/solve")
    public ResponseEntity<?> solvePack(@PathVariable Long packId) {
        MatchingRequestDTO dummyRequest = new MatchingRequestDTO();

        List<StudentCandidate> students = new ArrayList<>();
        students.add(new StudentCandidate("Student_1", List.of("Course_A", "Course_B")));
        students.add(new StudentCandidate("Student_2", List.of("Course_B", "Course_A")));
        dummyRequest.setStudents(students);

        List<CourseCapacity> courses = new ArrayList<>();
        courses.add(new CourseCapacity("Course_A", 10));
        courses.add(new CourseCapacity("Course_B", 10));
        dummyRequest.setCourses(courses);

        System.out.println("Triggering StableMatch for Pack ID: " + packId);

        var solution = matchingService.invokeMatchingService(dummyRequest);

        if (solution.isEmpty()) {
            return ResponseEntity.ok("Matching service is unavailable or found no solution (Fallback active).");
        }
        return ResponseEntity.ok(solution);
    }

}
