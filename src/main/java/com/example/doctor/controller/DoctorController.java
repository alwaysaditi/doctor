package com.example.doctor.controller;

import com.example.doctor.entity.Doctor;
import com.example.doctor.repository.DoctorRepository;
import com.example.doctor.repository.MemberDAO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    String authenticatedusername ;

    @Autowired
    private MemberDAO memberDAO;
    @GetMapping("/")
    public String doctorHome(Model model)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
       // System.out.println("username of the currently logged in user is "+authentication.getName()); // This returns the username
        // first check if doctor repository contains the currently logged in user. add the corresponding true/false attribute to the model.
        // if it doesnt then the doctor object has to be created and render a different webage for each case accordingly
        authenticatedusername = authentication.getName();
        if(doctorRepository.findByUsername(authentication.getName())!=null) //already a doctor exists
        {
            model.addAttribute("added",true);
            model.addAttribute("reftext","Welcome to the home page,"+doctorRepository.findByUsername(authenticatedusername).getFullName());

        }

        else
        {
            model.addAttribute("added",false);
            model.addAttribute("reftext","please configure your profile first!");
            model.addAttribute("newdoctor", new Doctor());
        }

        return "doctor-home";
    }

    @PostMapping("/registerdoc")
    @Transactional
    String registerDoctor(@ModelAttribute("newdoctor") Doctor newDoc, Model model) // this can be only for adding the doctors
            // details , whil another form in the same page can be used to add experiences and
            // eductaion, with a different model attribute. the username can be gotten by the currently logged in user;
    {
Doctor theSavedDoc = new Doctor(0,authenticatedusername,memberDAO.findEmailByUsername(authenticatedusername),newDoc.getFullName(), newDoc.getSpeciality());
        model.addAttribute("added",true);
        model.addAttribute("reftext","Welcome to the home page!, Dr "+newDoc.getFullName());
        doctorRepository.save(theSavedDoc);
        return "doctor-home";
    }



}
