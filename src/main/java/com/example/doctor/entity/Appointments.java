package com.example.doctor.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="user_id")
    private String user_id;

    // need  to have  a default constructor, and constructor with doc name and id
@Column(name="day_week")
private String day_week;

    @Column(name="slot1")
    private String slot1;

    @Column(name="slot2")
    private String slot2;

    @Column(name="slot3")
    private String slot3;

    @Column(name="slot4")
    private String slot4;
    @Column(name="slot5")
    private String slot5;

    @Column(name="slot6")
    private String slot6;

    @Column(name="slot7")
    private String slot7;

    @Column(name="slot8")
    private String slot8;

    public Appointments() {
    }

    public Appointments(int id, String user_id,String day_week) {
        this.id = id;
        this.user_id = user_id;
        this.day_week = day_week;
    }
}
