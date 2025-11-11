package com.example.Homework.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

@Entity
@Table(name="preferences", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "course_id"}))
public class Preference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(optional = false) @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(optional = false) @JoinColumn(name = "pack_id")
    private Pack pack;

    @Min(1)
    private int rank;

    @Min(0)
    private int tieGroup;

    @Version
    private int version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Pack getPack() {
        return pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getTieGroup() {
        return tieGroup;
    }

    public void setTieGroup(int tieGroup) {
        this.tieGroup = tieGroup;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
