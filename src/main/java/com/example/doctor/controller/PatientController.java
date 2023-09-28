package com.example.doctor.controller;

import com.example.doctor.entity.*;
import com.example.doctor.repository.*;
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

    String weekdayfin;

    String doctoruserid;


    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

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

         docdetails.add(new FormEntity(doctors.get(i),docdetRepo.findByUserId(doctors.get(i).getUsername())));

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
//doctorname = doctor.getFullName();
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
// HERE WE WRITE THE LOGIC FOR BOOKING AN APPOINTMENT AND CREATING AN OBJECT OF APPOINTMENT BASED ON THE USER ID OF THE PATIENT AND THE DOCTOR
        // AND WE THEN SEND THE REQUEST TO THE DOCTOR
        Appointments tempAppointment = appointmentRepository.findAppointment(doctoruserid,weekday);
        weekdayfin = weekday;
        List<String> emptySlots = new ArrayList<String>();
        if(tempAppointment.getSlot1()==null)
        {
            emptySlots.add("2:30 pm");
        }

        if(tempAppointment.getSlot2()==null)
        {
            emptySlots.add("3:00 pm");
        }

        if(tempAppointment.getSlot3()==null)
        {
            emptySlots.add("3:30 pm");
        }

        if(tempAppointment.getSlot4()==null)
        {
            emptySlots.add("4:00 pm");
        }

        if(tempAppointment.getSlot5()==null)
        {
            emptySlots.add("4:30 pm");
        }

        if(tempAppointment.getSlot6()==null)
        {
            emptySlots.add("5:00 pm");
        }

        if(tempAppointment.getSlot7()==null)
        {
            emptySlots.add("5:30 pm");
        }
        if(tempAppointment.getSlot8()==null)
        {
            emptySlots.add("6:00 pm");
        }
        model.addAttribute("emptyslots",emptySlots);
        System.out.println("emptyslotssize= "+emptySlots.size());
        return "appointment-patient";
    }

@PostMapping("/selectappointment")
    public String makeAppointment(@RequestParam("slot") String slot, Model model)
{
  model.addAttribute("doctor",doctoruserid);
  model.addAttribute("patient",authenticatedusername);
  model.addAttribute("slot",slot);
  model.addAttribute("weekday",weekdayfin);

  Appointments duplicateAppt = appointmentRepository.findDuplicate(doctoruserid,authenticatedusername);
  if(duplicateAppt==null)
  {
      model.addAttribute("allowed",true);
  }
//  if((duplicateAppt.getSlot1()==authenticatedusername)||(duplicateAppt.getSlot2()==authenticatedusername)
//          ||(duplicateAppt.getSlot3()==authenticatedusername)||(duplicateAppt.getSlot4()==authenticatedusername)||
//          (duplicateAppt.getSlot5()==authenticatedusername)||(duplicateAppt.getSlot6()==authenticatedusername)||
//          (duplicateAppt.getSlot7()==authenticatedusername)||(duplicateAppt.getSlot8()==authenticatedusername))
    else
  {
      model.addAttribute("day_week",duplicateAppt.getDay_week());
      model.addAttribute("allowed",false);
      String notallowed = "you alread have scheduled an appointment with doctor on"+duplicateAppt.getDay_week()+". Please try again for a later week";
      model.addAttribute("notallowedmessage",notallowed);
  }

  // now we need to create an appointment request object with the details above. patient should be able to view the status
    // of all the appointments with the selected doctors (should not book the same doctor more than once)
    // and doctor should be able to view and approve/reject all appointment requests
    // here only you should check whether a user has already booked an appointment with the same doctor within the duration of a week
    // and reject the request if he has
  return "selected-appointment";
}

}
