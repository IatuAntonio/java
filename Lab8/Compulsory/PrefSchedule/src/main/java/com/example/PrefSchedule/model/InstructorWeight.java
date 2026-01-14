package com.example.PrefSchedule.model;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor_weights")
public class InstructorWeight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "optional_course_id")
    private Course optionalCourse;

    private String compulsoryAbbr;

    private Double weight;

    public InstructorWeight() {}

    public InstructorWeight(Course optionalCourse, String compulsoryAbbr, Double weight) {
        this.id = id;
        this.optionalCourse = optionalCourse;
        this.compulsoryAbbr = compulsoryAbbr;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Course getOptionalCourse() {
        return optionalCourse;
    }

    public void setOptionalCourse(Course optionalCourse) {
        this.optionalCourse = optionalCourse;
    }

    public String getCompulsoryAbbr() {
        return compulsoryAbbr;
    }

    public void setCompulsoryAbbr(String compulsoryAbbr) {
        this.compulsoryAbbr = compulsoryAbbr;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }
}
