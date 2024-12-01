package com.clinic_system.clinic_alshifa.controller;

import com.clinic_system.clinic_alshifa.model.MyAppUser;
import com.clinic_system.clinic_alshifa.model.MyAppUserRepository;
import com.clinic_system.clinic_alshifa.model.MyAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    @Autowired
    private MyAppUserRepository myAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private MyAppUserService myAppUserService;

//    @PostMapping(value = "/req/signup", consumes = "application/json")
//    public MyAppUser createUser(@RequestBody MyAppUser user){
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return myAppUserRepository.save(user);
//    }

    @PostMapping("/req/signup")
    public ResponseEntity<String> registerUser(@RequestBody MyAppUser user) {
        try {
            // Save user to the database, ensure password is encoded
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            myAppUserRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering user: " + e.getMessage());
        }
    }


}

