package com.clinic_system.clinic_alshifa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DoctorController {

    @GetMapping("/doctor-dashboard")
    public String doctorDashboard() {
        return "doctor-dashboard";
    }
}
