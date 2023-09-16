package com.example.doctor.controller;

import com.example.doctor.entity.Roles;
import com.example.doctor.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    List<Roles> user_roleList;
    @GetMapping("/assignusers")
    public String assignuserstoRoles(Model model)
    {
        user_roleList = rolesRepository.user_roles();
       model.addAttribute("userlist",user_roleList); // this form sends each individual


       return "assign-roles";
    }

    @PostMapping("/updaterole")
    public String assignRoles(@RequestParam("userId") String userid, @RequestParam("role") String userrole, Model model)
    {
        Roles updatedRole = new Roles(userid,userrole);
        rolesRepository.save(updatedRole);
        user_roleList = rolesRepository.user_roles();
        model.addAttribute("userlist",user_roleList);
        return "assign-roles";
    }
}