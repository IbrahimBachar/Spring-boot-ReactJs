package com.clinic_system.clinic_alshifa.controller;

import com.clinic_system.clinic_alshifa.model.MyAppUser;
import com.clinic_system.clinic_alshifa.model.MyAppUserRepository;
import com.clinic_system.clinic_alshifa.model.MyAppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    MyAppUserRepository myAppUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    MyAppUserService myAppUserService;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody MyAppUser user) {

        try {
            // Save user to the database, ensure password is encoded
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            myAppUserRepository.save(user);
            return ResponseEntity.ok("User registered successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error registering user: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody String username, String password) {
        try {
            logger.info("Login attempt for username: {}", username);

            MyAppUser user = myAppUserService.findByUsername(username);

            if (user != null && passwordEncoder.matches(password, user.getPassword())) {
                logger.info("Password matches for user: {}", username);

                // Generate JWT token
                String token = jwtTokenProvider.createToken(user.getUsername(), List.of("ROLE_USER"));

                Map<String, String> response = new HashMap<>();
                response.put("message", "Login successful");
                response.put("token", token);  // Include the token here

                logger.info("User '{}' logged in successfully ", username);
                return ResponseEntity.ok(response);
            } else {
                logger.warn("Invalid username or password for user: {}", username);
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
            }
        } catch (Exception e) {
            logger.error("Unexpected error during login for username '{}': {}", username, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login.");
        }
    }

//    @GetMapping("/admin/users")
//    @PreAuthorize("hasRole('ADMIN')")
//    public List<User> getAllUsers() {
//        // Return users for admin
//    }
}
