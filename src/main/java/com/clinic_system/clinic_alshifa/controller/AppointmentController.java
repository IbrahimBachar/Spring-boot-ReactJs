package com.clinic_system.clinic_alshifa.controller;

import com.clinic_system.clinic_alshifa.model.Appointment;
import com.clinic_system.clinic_alshifa.model.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("/new")
    public String showAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        return "appointment-form";
    }

    @PostMapping("/new")
    public String createAppointment(@ModelAttribute Appointment appointment,
                                    @RequestParam("file") MultipartFile file,
                                    Model model) throws IOException {
        if (!file.isEmpty()) {
            appointment.setFile(file.getBytes());
            appointment.setFileName(file.getOriginalFilename());
        }

        appointmentService.saveAppointment(appointment);
        model.addAttribute("message", "Appointment scheduled successfully!");
        return "redirect:/appointments/new";
    }
}

