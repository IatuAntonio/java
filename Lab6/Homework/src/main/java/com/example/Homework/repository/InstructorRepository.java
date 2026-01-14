package com.example.Homework.repository;


import com.example.Homework.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorRepository  extends JpaRepository<Instructor, Long> {

    Instructor findByEmail(String email);

}
