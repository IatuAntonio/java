package com.example.Homework.model;


import jakarta.persistence.*;

@Entity
@Table(name = "students")
public class Student extends Person{

    private String code;
    private int year;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
