package com.example.Compulsory.controller;

import com.example.Compulsory.dto.LoginRequestDTO;
import com.example.Compulsory.model.Pack;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDTO request){
        return ResponseEntity.ok("Login successful for user: " + request.getUsername());
    }

}
