package com.example.PrefSchedule.service;

import com.example.PrefSchedule.model.Pack;
import com.example.PrefSchedule.repository.PackRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackService {

    private final PackRepository packRepository;

    public PackService(PackRepository packRepository) {
        this.packRepository = packRepository;
    }

    public List<Pack> findAllPacks() {
        return packRepository.findAll();
    }

    public List<Pack> findByYear(int year) {
        return packRepository.findByYear(year);
    }

    public Pack savePack(Pack pack) {
        return packRepository.save(pack);
    }

    public void deletePack(Long id) {
        packRepository.deleteById(id);
    }

}
