package com.example.StableMatch.service;

import com.example.StableMatch.dto.MatchPair;
import com.example.StableMatch.dto.MatchingRequestDTO;
import com.example.StableMatch.dto.StudentCandidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SolverService {



    public List<MatchPair> solve (MatchingRequestDTO problem) {
        List<MatchPair> solution = new ArrayList<>();

        for (StudentCandidate student : problem.getStudents()) {
            if (student.getPreferences() != null &&  !student.getPreferences().isEmpty()) {
                String firstOption = student.getPreferences().getFirst();
                solution.add(new MatchPair(student.getId(), firstOption));
            } else {
                solution.add(new MatchPair(student.getId(), null));
            }
        }

        return solution;
    }

}
