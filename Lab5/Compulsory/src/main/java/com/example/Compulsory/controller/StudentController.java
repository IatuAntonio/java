package com.example.Compulsory.controller;


import com.example.Compulsory.model.Student;
import com.example.Compulsory.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService students;

    public StudentController(StudentService students) {
        this.students = students;
    }

    @PostMapping
    public ResponseEntity<Student> create(@Valid @RequestBody Student payload) {
        Student createdStudent = students.saveStudent(payload);
        return ResponseEntity.created(URI.create("/api/students/" + createdStudent.getId())).body(createdStudent);
    }

    @GetMapping
    public List<Student> findAll(@RequestParam(required = false) Integer year) {
        if (year != null) {
            return students.findStudentsByYear(year);
        }
        return students.findAllStudents();
    }

    @GetMapping("/{id}")
    public Student findOne(@PathVariable Long id) {
        return students.findAllStudents()
                .stream().filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student payload) {
        Student existingStudent = findOne(id);
        existingStudent.setCode(payload.getCode());
        existingStudent.setName(payload.getName());
        existingStudent.setYear(payload.getYear());
        existingStudent.setEmail(payload.getEmail());

        return students.saveStudent(existingStudent);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        findOne(id);
        students.deleteStudent(id);
    }


}
