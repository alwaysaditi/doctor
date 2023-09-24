package com.example.doctor.controller;

import com.example.doctor.entity.*;
import com.example.doctor.repository.DoctorDetails;
import com.example.doctor.repository.DoctorRepository;
import com.example.doctor.repository.MemberDAO;
import com.example.doctor.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/patient")
public class PatientController {

    String authenticatedusername ;

    String doctorname;

    String doctoruserid;


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private DoctorDetails docdetRepo;
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

        List<Doctor> doctors = doctorRepository.findBySpeciality(speciality);
        System.out.println("size of matching= "+doctors.size());
        List<FormEntity> docdetails = new ArrayList<>();
        for(int i=0;i< doctors.size();i++)
        {
          //  System.out.println("doctor name = "+doctors.get(i).getFullName());
         docdetails.add(new FormEntity(doctors.get(i),docdetRepo.findByUserId(doctors.get(i).getUsername())));
//         System.out.println(docdetRepo.findByUserId(doctors.get(i).getUsername()).get(0));
//            System.out.println(docdetRepo.findByUserId(doctors.get(i).getUsername()).get(1));
//            System.out.println(docdetRepo.findByUserId(doctors.get(i).getUsername()).get(2));
//            System.out.println(docdetRepo.findByUserId(doctors.get(i).getUsername()).get(3));
        }

        model.addAttribute("specdoc",docdetails);
// need to fetch list of doctors and docdets satisfying a speciality
        // once u fetch a list of doctors, fetch list of corresponding docdets with that username
return "doctors-speciality";
    }

    @GetMapping("/appointment")
    public String bookAppointment(@RequestParam(required=false,name = "username") String username, Model model) {

Doctor doctor = doctorRepository.findByUsername(username);


        model.addAttribute("selecteday",false);
doctorname = doctor.getFullName();
doctoruserid = doctor.getUsername();
        model.addAttribute("doctoruserid",doctoruserid);
        //conditionally display a dropdown of day of the week depending on whether a day has been selected or not

        return "appointment-patient";
    }
    // try to set the weekday variable to null after it's value has been used

    @PostMapping("/chooseday")
    public String chooseday(@ModelAttribute("weekday")String weekday,Model model)
    {
        model.addAttribute("weekday",weekday);

        model.addAttribute("doctoruserid",doctoruserid);
        model.addAttribute("selecteday",true);
        return "appointment-patient";
    }



}
