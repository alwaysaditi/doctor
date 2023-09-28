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

    public String getSlot1() {
        return slot1;
    }

    public void setSlot1(String slot1) {
        this.slot1 = slot1;
    }

    public String getSlot2() {
        return slot2;
    }

    public void setSlot2(String slot2) {
        this.slot2 = slot2;
    }

    public String getSlot3() {
        return slot3;
    }

    public void setSlot3(String slot3) {
        this.slot3 = slot3;
    }

    public String getSlot4() {
        return slot4;
    }

    public void setSlot4(String slot4) {
        this.slot4 = slot4;
    }

    public String getSlot5() {
        return slot5;
    }

    public void setSlot5(String slot5) {
        this.slot5 = slot5;
    }

    public String getSlot6() {
        return slot6;
    }

    public void setSlot6(String slot6) {
        this.slot6 = slot6;
    }

    public String getSlot7() {
        return slot7;
    }

    public void setSlot7(String slot7) {
        this.slot7 = slot7;
    }

    public String getSlot8() {
        return slot8;
    }

    public void setSlot8(String slot8) {
        this.slot8 = slot8;
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

    public String getDay_week() {
        return day_week;
    }

    public void setDay_week(String day_week) {
        this.day_week = day_week;
    }
}
