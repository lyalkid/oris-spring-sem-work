package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.CardService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProfileController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CardService cardService;

    @GetMapping("/my_profile")
    public String viewProfilePage(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        Student student = studentService.findByUsername(userDetails.getUsername()).orElse(null);
        if (student == null) {
            return "redirect:/login";
        }

        int cardCount = cardService.getCardsByStudentId(student.getId()).size();
        model.addAttribute("student", student);
        model.addAttribute("cardCount", cardCount);

        return "my_profile";
    }

    @PostMapping("/my_profile/update")
    public String updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                                @ModelAttribute("student") Student updatedStudent) {
        Student student = studentService.findByUsername(userDetails.getUsername()).orElse(null);
        if (student == null) {
            return "redirect:/login";
        }

        student.setDescription(updatedStudent.getDescription());
        studentService.save(student);

        return "redirect:/my_profile";
    }
}
