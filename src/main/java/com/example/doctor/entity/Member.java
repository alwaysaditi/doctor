package com.example.doctor.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.text.SimpleDateFormat;

@Entity
@Table(name = "members")
public class Member {

    @Id
@Column(name = "user_id")
    private String userName;

@Column(name="pw")
    private String password;

@Column(name = "full_name")
    private String fullName;

    public Member() {
    }



@Column(name = "email")
    private String email;



    public Member(String userName, String password, String fullName,String email) {
        this.userName = userName;
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Hash the password
        String hashedPassword = passwordEncoder.encode(password);
        this.password = "{bcrypt}"+hashedPassword;
        this.fullName = fullName;

        this.email = email;
        //this.dateOfBirth = dateOfBirth;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        // Hash the password
        String hashedPassword = passwordEncoder.encode(password);
        this.password = "{bcrypt}"+hashedPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }




}
