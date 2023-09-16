package com.example.doctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/pharmacist")
public class PharmacistController {
    @GetMapping("/") // this is the second page after succesful login, the first being the welcome pag
    // this page can be customised for each user. basically filling up details
    public String pharmaHome()
    {
        return "pharmacist-home";
    }
}