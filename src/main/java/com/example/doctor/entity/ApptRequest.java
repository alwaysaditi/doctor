package com.example.doctor.entity;

import jakarta.persistence.*;

@Table
@Entity(name="a_requests")
public class ApptRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "doc_id")
    private String doc_id;

    @Column(name = "user_id")
    private String user_id;

    @Column(name = "slot")
    private String slot;


    @Column(name = "day_week")
    private String day_week;

    public enum Status {
        ACCEPT, REJECT, PENDING
    }

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    // Getter and Setter for status
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ApptRequest(int id, String doc_id, String user_id, String slot, String day_week, Status status) {
        this.id = id;
        this.doc_id = doc_id;
        this.user_id = user_id;
        this.slot = slot;
        this.day_week = day_week;
        this.status = status;
    }

    public ApptRequest( String doc_id, String user_id, String slot, String day_week) {

        this.doc_id = doc_id;
        this.user_id = user_id;
        this.slot = slot;
        this.day_week = day_week;
    }

    public ApptRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSlot() {
        return slot;
    }

    public void setSlot(String slot) {
        this.slot = slot;
    }

    public String getDay_week() {
        return day_week;
    }

    public void setDay_week(String day_week) {
        this.day_week = day_week;
    }

    public Status getStatusFromString(String statusString) {
        switch (statusString.toUpperCase()) {
            case "ACCEPT":
                return Status.ACCEPT;
            case "REJECT":
                return Status.REJECT;
            case "PENDING":
                return Status.PENDING;
            default:
                throw new IllegalArgumentException("Invalid status value: " + statusString);

        }

    }

}
