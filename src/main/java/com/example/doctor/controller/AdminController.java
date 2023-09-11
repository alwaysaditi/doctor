package com.example.doctor.controller;

import com.example.doctor.entity.Roles;
import com.example.doctor.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private RolesRepository rolesRepository;
    @GetMapping("/")
    public String adminHome()
    {
        return "admin-home";
    }

    @GetMapping("/assignusers")
    public String assignuserstoRoles(Model model)
    {
        List<Roles> user_roleList = rolesRepository.user_roles();
       model.addAttribute("userlist",user_roleList);

       return "assign-roles";
    }
}