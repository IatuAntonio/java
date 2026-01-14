package com.example.StableMatch.controller;

import com.example.StableMatch.dto.MatchPair;
import com.example.StableMatch.dto.MatchingRequestDTO;
import com.example.StableMatch.service.SolverService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solver")
public class MatchingController {

    private final SolverService solverService;

    public MatchingController(SolverService solverService) {
        this.solverService = solverService;
    }

    @PostMapping("/solve")
    public List<MatchPair> solveProblem(@RequestBody MatchingRequestDTO problem) {
        System.out.println("Received problem with " + problem.getStudents().size() + " students.");
        return solverService.solve(problem);
    }

    @GetMapping("/assignments")
    public List<MatchPair> getAllAssignments() {
        return solverService.getLastSolution();
    }

    @GetMapping("/assignments/{studentId}")
    public MatchPair getAssignmentForStudent(@PathVariable String studentId) {
        return solverService.getLastSolution().stream()
                .filter(p -> p.getStudentId().equals(studentId))
                .findFirst()
                .orElse(null);
    }

}
