package com.clinic_system.clinic_alshifa.controller;

import com.clinic_system.clinic_alshifa.model.MyAppUser;
import com.clinic_system.clinic_alshifa.model.MyAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody MyAppUser user) {
        // Save user to the database
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        myAppUserRepository.save(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyAppUserRepository myAppUserRepository;
}
