package com.clinic_system.clinic_alshifa.controller;

import com.clinic_system.clinic_alshifa.model.MyAppUser;
import com.clinic_system.clinic_alshifa.model.MyAppUserService;
import com.clinic_system.clinic_alshifa.model.PDFGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
//@Controller
//@RequestMapping("/admin")  //This mapping was only admin
public class AdminController {

    @Autowired
    private MyAppUserService userService;

    @GetMapping("/admin-dashboard")  //This mapping was only dashboard
    public String showUserList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortField,
            @RequestParam(defaultValue = "asc") String sortDir,
            Model model
    ) {

        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDir), sortField));
        List<MyAppUser> users = userService.getAllUsers();
        model.addAttribute("users", users);
        Page<MyAppUser> usersPage = userService.getUsersPage(PageRequest.of(page, size));
        model.addAttribute("users", usersPage.getContent());
        model.addAttribute("currentPage", usersPage.getNumber());
        model.addAttribute("totalPages", usersPage.getTotalPages());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        return "admin-dashboard";  // Return the Thymeleaf template
    }


    @PostMapping("/api/users/{id}/delete")
    public String deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
        return "redirect:/admin-dashboard";
    }

    // Edit user (render form)
    @GetMapping("/edit/{id}")
    public String showEditUserForm(@PathVariable Long id, Model model) {
        MyAppUser user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "edit-user";  // Return a form to edit the user
    }

    // Update user
    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute MyAppUser updatedUser) {
        userService.updateUser(id, updatedUser);
        return "redirect:/admin-dashboard";  // Redirect to user list after update
    }


    @Autowired
    private PDFGeneratorService pdfGeneratorService;
    // PDF Generation Endpoint
    @GetMapping("/download-pdf")
    public ResponseEntity<InputStreamResource> downloadPDF() {
        List<MyAppUser> users = userService.getAllUsers();
        ByteArrayInputStream pdf = pdfGeneratorService.generateUserListPDF(users);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=user_list.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(pdf));
    }
}
