package com.example.Homework.repository;

import com.example.Homework.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

}
