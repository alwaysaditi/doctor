package com.example.doctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/nurse")
public class NurseController {
    @GetMapping("/")
    public String nurseHome()
    {
        return "nurse-home";
    }
}
