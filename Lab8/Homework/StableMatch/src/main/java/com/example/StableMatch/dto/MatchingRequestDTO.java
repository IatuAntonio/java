package com.example.StableMatch.dto;

import java.util.List;

public class MatchingRequestDTO {

    private List<StudentCandidate> students;
    private List<CourseCapacity> courses;

    public List<StudentCandidate> getStudents() {
        return students;
    }

    public void setStudents(List<StudentCandidate> students) {
        this.students = students;
    }

    public List<CourseCapacity> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseCapacity> courses) {
        this.courses = courses;
    }
}
