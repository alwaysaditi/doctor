package com.example.doctor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/loginForm") // this URL mentioned in security config file
    public String loginForm()
    {
        return "login-form";
    }

    @GetMapping("/access-denied") // this URL mentioned in security config file
    public String accessDenied()
    {
        return "access-denied";
    }
}
