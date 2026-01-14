package com.example.Homework.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class PreferenceRequestDTO {

    @NotNull private Long studentId;
    @NotNull private Long courseId;
    @NotNull private Long packId;
    @Min(1)  private int rank;
    @Min(0)  private int tieGroup;

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Long getPackId() {
        return packId;
    }

    public void setPackId(Long packId) {
        this.packId = packId;
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

}
