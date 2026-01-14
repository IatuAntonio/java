package com.example.PrefSchedule.repository;

import com.example.PrefSchedule.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Person, Long> {

    Optional<Person> findByEmail(String email);

}
