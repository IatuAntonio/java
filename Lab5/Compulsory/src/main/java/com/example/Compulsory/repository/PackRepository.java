package com.example.Compulsory.repository;

import com.example.Compulsory.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PackRepository  extends JpaRepository<Pack, Long> {

    List<Pack> findByYear(int year);

}
