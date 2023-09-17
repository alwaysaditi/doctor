package com.example.doctor.entity;

import jakarta.persistence.*;

@Entity
@Table(name="doctors")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private String username;

    @Column(name = "email")
    private String email;


    @Column(name = "speciality")
    private String speciality;
    @Column(name = "full_name")
    private String fullName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Doctor(int id, String username, String email, String fullName, String speciality) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.speciality = speciality;
    }

    public Doctor() {
    }
}
