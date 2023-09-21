package com.example.doctor.controller;

import com.example.doctor.entity.DocDet;
import com.example.doctor.entity.Doctor;
import com.example.doctor.entity.FormEntity;
import com.example.doctor.repository.DoctorDetails;
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

import java.util.List;

@Controller
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    String authenticatedusername ;

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private DoctorDetails doctorDetails;
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
            FormEntity formEntity = new FormEntity(new Doctor(), new DocDet(),new DocDet(), new DocDet(),new DocDet());
            model.addAttribute("formentity", formEntity);
            //System.out.println("no such doctor yet exists");
        }

        return "doctor-home";
    }

    @PostMapping("/registerdoc")
    @Transactional
    String registerDoctor(@ModelAttribute("formentity")FormEntity formEntity, Model model) // this can be only for adding the doctors
            // details , whil another form in the same page can be used to add experiences and
            // eductaion, with a different model attribute. the username can be gotten by the currently logged in user;
    {
        System.out.println("entered here!");
Doctor theSavedDoc = new Doctor(0,authenticatedusername,memberDAO.findEmailByUsername(authenticatedusername),formEntity.getDoctor().getFullName(), formEntity.getDoctor().getSpeciality());
DocDet bachelorsDegree = new DocDet(0,authenticatedusername,"DEGREE",formEntity.getDocdet1().getEntry());
DocDet mastersDegree = new DocDet(0,authenticatedusername,"DEGREE",formEntity.getDocdet2().getEntry());
DocDet phDegree = new DocDet(0,authenticatedusername,"DEGREE",formEntity.getDocdet3().getEntry());
        DocDet experience = new DocDet(0,authenticatedusername,"EXPERIENCE",formEntity.getDocdet4().getEntry());
        model.addAttribute("added",true);
        model.addAttribute("reftext","Welcome to the home page!, Dr "+formEntity.getDoctor().getFullName());
        doctorRepository.save(theSavedDoc);
        doctorDetails.save(bachelorsDegree);
        doctorDetails.save(mastersDegree);
        doctorDetails.save(phDegree);
        doctorDetails.save(experience);
        return "doctor-home";
    }

    @GetMapping("/viewdoc")
    public String viewDoctor(Model docmodel)
    {
        Doctor currDoc = doctorRepository.findByUsername(authenticatedusername);
       List<DocDet> docdet=  doctorDetails.findByUserId(authenticatedusername);
        docmodel.addAttribute("fullname",currDoc.getFullName());
        docmodel.addAttribute("email",currDoc.getEmail());
        docmodel.addAttribute("speciality",currDoc.getSpeciality());
 docmodel.addAttribute("bachelors",docdet.get(0).getEntry());
 docmodel.addAttribute("masters",docdet.get(1).getEntry());
 docmodel.addAttribute("phd",docdet.get(2).getEntry());
        docmodel.addAttribute("experience",docdet.get(3).getEntry());
 return "doc-profile";

    }


}
