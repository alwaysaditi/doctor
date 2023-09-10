package com.example.doctor.controller;


import com.example.doctor.entity.Member;
import com.example.doctor.entity.Roles;
import com.example.doctor.repository.MemberDAO;
//import com.example.doctor.repository.MemberRepository;
import com.example.doctor.repository.RolesRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    @Autowired
    private MemberDAO memberRepository;

    @Autowired
            private RolesRepository rolesRepository;
    boolean duplicate = false;
    @GetMapping("/signupForm") //to render any page you always need to use getMapping
        // you can only modify the thymeleaf variables in those functions that have an explicit mapping for
        // the corresponding page
    String signupForm(Model model)
    {

        Member member= new Member();
        model.addAttribute("newmember",member); // no data stored in this variable
        //model.addAttribute("errorMessage",email_reg);// user variable will be used in the signupform
        return "signupform";
    }


    @PostMapping("/signup")
    String createNewEmployee(@ModelAttribute("newmember") Member theMember, Model model, HttpSession session) // the signupform.html
    // sends data to this function. so not possible to modify data inside this function
    {
// the body passed as JSON object will be automatically converted to a Java POJO object

        Member theSavedMember = theMember;
        System.out.println("the Member's details= "+theMember.toString());
        try
        {
            //theSavedMember.setId(0);
            memberRepository.save(theSavedMember);
            //System.out.println("Member name of new Member "+theSavedMember.getMembername());
            duplicate = false;
            model.addAttribute("duplicate",duplicate); // if duplicate is false then
            // u can also print login success!
           rolesRepository.save(new Roles(theSavedMember.getUserName(),"ROLE_USER"));
           model.addAttribute("signsuccess","Sign up successful!");
           session.setAttribute("newmember",theMember);
            return "home";
        }

        catch (Exception e )
        {

            System.out.println("duplicate email or Membername");
            duplicate = true;
            model.addAttribute("email_reg",duplicate);
            model.addAttribute("errorMess","An account with the email/Membername already exists. Please login or try again");
            return "signupform";


        }



    }



}
