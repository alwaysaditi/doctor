package com.example.doctor.controller;

import com.example.doctor.entity.*;
import com.example.doctor.repository.MemberDAO;
import com.example.doctor.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {

    String authenticatedusername ;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MemberDAO memberDAO;
    @GetMapping("/")
    public String patientHome(Model model)
    {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // System.out.println("username of the currently logged in user is "+authentication.getName()); // This returns the username
        // first check if doctor repository contains the currently logged in user. add the corresponding true/false attribute to the model.
        // if it doesnt then the doctor object has to be created and render a different webage for each case accordingly
        authenticatedusername = authentication.getName();
        if(patientRepository.findByUsername(authentication.getName())!=null) //already a doctor exists
        {
            model.addAttribute("added",true);
            model.addAttribute("reftext","Welcome to the home page,"+patientRepository.findByUsername(authenticatedusername).getName());

        }

        else
        {
            model.addAttribute("added",false);
            model.addAttribute("reftext","please configure your profile first!");
            //FormEntity formEntity = new FormEntity(new Doctor(), new DocDet(),new DocDet(), new DocDet(),new DocDet());
           Member patient = memberDAO.findByUserName(authenticatedusername);
            Patient newpatient = new Patient(patient.getUserName(),patient.getFullName(),patient.getEmail());
            model.addAttribute("patient", newpatient);
            //System.out.println("no such doctor yet exists");
        }



        return "patient-home";
    }

    @PostMapping("/registerpatient")
    public String registerpatient(@ModelAttribute("patient")Patient patient, Model model)
    {
Patient theSavedPatient = new Patient(0,patient.getUser_id(),patient.getName(),patient.getDob(),patient.getEmail(),patient.getBloodgroup());

        patientRepository.save(theSavedPatient);
model.addAttribute("added",true);
        model.addAttribute("reftext","Welcome to the home page,"+patientRepository.findByUsername(authenticatedusername).getName());
model.addAttribute("speciality","");
return "patient-home";
    }

    @PostMapping("/specialitypost")
    public String getDoctorsBySpeciality(@ModelAttribute("speciality")String speciality, Model model)
    {
        System.out.println("The speciality chosen is "+speciality);

        model.addAttribute("newspecial",speciality);

return "doctors-speciality";
    }
}
