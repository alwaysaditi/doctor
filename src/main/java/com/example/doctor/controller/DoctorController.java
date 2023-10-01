package com.example.doctor.controller;

import com.example.doctor.entity.*;
import com.example.doctor.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentReq appointmentReq;

    List<ApptRequest> pendingRequests;

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
        Appointments day1 = new Appointments(0,authenticatedusername,"MONDAY");
        Appointments day2 = new Appointments(0,authenticatedusername,"TUESDAY");
        Appointments day3 = new Appointments(0,authenticatedusername,"WEDNESDAY");
        Appointments day4 = new Appointments(0,authenticatedusername,"THURSDAY");
        Appointments day5 = new Appointments(0,authenticatedusername,"FRIDAY");
        model.addAttribute("added",true);
        model.addAttribute("reftext","Welcome to the home page!, Dr "+formEntity.getDoctor().getFullName());
        doctorRepository.save(theSavedDoc);
        doctorDetails.save(bachelorsDegree);
        doctorDetails.save(mastersDegree);
        doctorDetails.save(phDegree);
        doctorDetails.save(experience);
        appointmentRepository.save(day1);
        appointmentRepository.save(day2);
        appointmentRepository.save(day3);
        appointmentRepository.save(day4);
        appointmentRepository.save(day5);
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

    @GetMapping("/viewrequests")
    public String viewPendingRequests(Model model)
    {
        // this line is used to gather the list of pending requests for a particular doctor
        pendingRequests = appointmentReq.findApptReqs(authenticatedusername,"PENDING");

        model.addAttribute("pendingrequests",pendingRequests);

        return "view-requests";
    }

    @PostMapping("/approverequest")
    public String approveRequests(@ModelAttribute("doc_id")String doc_id, @ModelAttribute("user_id")String user_id,@ModelAttribute("slot")String slot,@ModelAttribute("day_week")String day_week,@RequestParam("status") String acceptedstatus, Model model)
    {
        System.out.println("request doctor = "+doc_id);

       ApptRequest newRequest = new ApptRequest(doc_id,user_id,slot,day_week);
       newRequest.setStatus(newRequest.getStatusFromString(acceptedstatus));
       appointmentReq.save(newRequest); // although this method can be used to update pending requests , it is not getting updated because
        // no data is being passed from request object
//        ApptRequest oldRequest = new ApptRequest(doc_id,user_id,slot,day_week);
//        oldRequest.setStatus(oldRequest.getStatusFromString("PENDING"));
        appointmentReq.removePending(doc_id,user_id,"PENDING");
        pendingRequests = appointmentReq.findApptReqs(doc_id,"PENDING");
model.addAttribute("pendingrequests",pendingRequests);
        return "view-requests";
    }

}
