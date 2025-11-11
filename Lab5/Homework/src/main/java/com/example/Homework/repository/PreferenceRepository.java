package com.example.Homework.repository;

import com.example.Homework.model.Preference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PreferenceRepository extends JpaRepository<Preference, Long> {

    List<Preference> findByStudent_IdAndPack_IdOrderByRankAsc(Long studentId, Long packId);
    Optional<Preference> findByStudent_IdAndCourse_Id(Long studentId, Long courseId);

}
