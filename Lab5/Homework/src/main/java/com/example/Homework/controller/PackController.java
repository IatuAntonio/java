package com.example.Homework.controller;


import com.example.Homework.model.Pack;
import com.example.Homework.service.PackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/packs")
public class PackController {

    private final PackService packService;

    public PackController(PackService packService) {
        this.packService = packService;
    }

    @PostMapping
    public ResponseEntity<Pack> create(@RequestBody Pack payload) {
        Pack saved = packService.savePack(payload);
        return ResponseEntity.created(URI.create("/api/packs/" + saved.getId())).body(saved);
    }

    @GetMapping
    public List<Pack> findAll() {
        return packService.findAllPacks();
    }

    @GetMapping("/{id}")
    public Pack findOne(@PathVariable Long id) {
        return packService.findAllPacks().stream().filter(p -> p.getId().equals(id)).findFirst().orElse(null);
    }

}
