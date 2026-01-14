package com.example.QuickGrade.controller;

import com.example.QuickGrade.dto.GradeEventDTO;
import com.example.QuickGrade.service.GradePublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grades")
public class GradesController {

    private final GradePublisherService gradePublisherService;
    public GradesController(GradePublisherService gradePublisherService) {
        this.gradePublisherService = gradePublisherService;
    }

    @PostMapping("/publish")
    public ResponseEntity<String> publish(@RequestBody GradeEventDTO gradeEvent) {
        gradePublisherService.publishGradeEvent(gradeEvent);
        return ResponseEntity.ok("Grade event published successfully");
    }

}
