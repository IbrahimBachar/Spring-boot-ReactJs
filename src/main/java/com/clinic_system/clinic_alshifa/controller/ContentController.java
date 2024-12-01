package com.clinic_system.clinic_alshifa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/users")
public class ContentController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @GetMapping("/addUser")
    public String addUser(){
        return "addUser";
    }

    @GetMapping("/edit-user")
    public String editUser(){
        return "edit-user";
    }

    @GetMapping("/auth/forgot-password")
    public String forgotPassword(){
        return "forgot-password";
    }

//    @GetMapping("/admin-dashboard")
//    public String admin(){
//        return "admin-dashboard";
//    }
//
//    @GetMapping("/doctor/dashboard")
//    public String doctor(){
//        return "doctor-dashboard";
//    }
//
//    @GetMapping("/patient/dashboard")
//    public String patient(){
//        return "patient-dashboard";
//    }

}