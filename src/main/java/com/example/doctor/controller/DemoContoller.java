package com.example.doctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoContoller {

    @GetMapping("/home")
    private String showHome()
    {
        return "home";
    }



}
