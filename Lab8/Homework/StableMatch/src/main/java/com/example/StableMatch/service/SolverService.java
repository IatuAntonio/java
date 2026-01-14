package com.example.StableMatch.service;

import com.example.StableMatch.dto.CourseCapacity;
import com.example.StableMatch.dto.MatchPair;
import com.example.StableMatch.dto.MatchingRequestDTO;
import com.example.StableMatch.dto.StudentCandidate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SolverService {

    private List<MatchPair> lastSolution = new ArrayList<>();

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

    public List<MatchPair> solveRandom(MatchingRequestDTO problem) {
        List<MatchPair> solution = new ArrayList<>();

        List<StudentCandidate> shuffleStudents = new ArrayList<>(problem.getStudents());
        Collections.shuffle(shuffleStudents);

        Map<String, Integer> capacities = new HashMap<>();
        for (CourseCapacity cc : problem.getCourses()) {
            capacities.put(cc.getId(), cc.getCapacity());
        }

        List<String> courseIds = new ArrayList<>(capacities.keySet());

        for (StudentCandidate student : shuffleStudents) {
            boolean assigned = false;

            Collections.shuffle(courseIds);

            for (String cid : courseIds) {
                if (capacities.get(cid) > 0) {
                    solution.add(new MatchPair(student.getId(), cid));
                    capacities.put(cid, capacities.get(cid) - 1);
                    assigned = true;
                    break;
                }
            }

            if (!assigned) {
                solution.add(new MatchPair(student.getId(), null));
            }
        }
        this.lastSolution = solution;
        return solution;
    }

    public List<MatchPair> getLastSolution() {
        return lastSolution;
    }

}
