package com.example.doctor.entity;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "patient")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "name")
    private String name;



    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name ="dob")
    private Date dob;



    @Column(name = "email")
    private String email;


    @Column(name = "age")
    private int age;


    @Column(name = "bloodgroup")
    private String bloodgroup;


    public Patient(int id, String user_id, String name, Date dob, String email, String bloodgroup) {
        this.id = id;
        this.user_id = user_id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.bloodgroup = bloodgroup;
        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(dob);
        Calendar currentCalendar = Calendar.getInstance();
        int years = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (currentCalendar.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            years--;
        }

        this.age = years;
    }

    public Patient(String user_id, String name, String email) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
    }

    public Patient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBloodgroup() {
        return bloodgroup;
    }

    public void setBloodgroup(String bloodgroup) {
        this.bloodgroup = bloodgroup;
    }

    public int getAge() {
        return age;
    }

    public void setAge(Date dob) {

        Calendar birthCalendar = Calendar.getInstance();
        birthCalendar.setTime(dob);
        Calendar currentCalendar = Calendar.getInstance();
        int years = currentCalendar.get(Calendar.YEAR) - birthCalendar.get(Calendar.YEAR);

        if (currentCalendar.get(Calendar.DAY_OF_YEAR) < birthCalendar.get(Calendar.DAY_OF_YEAR)) {
            years--;
        }

        this.age = years;
    }
}
