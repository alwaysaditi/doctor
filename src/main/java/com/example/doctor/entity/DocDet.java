package com.example.doctor.entity;

import jakarta.persistence.*;

@Table(name = "doctors_det")
@Entity
public class DocDet {

    @Id
    @Column(name="user_id")
    private String username;

    @Column(name = "type_entry")
    private String type_entry;

    @Column(name="entry")
    private String entry;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType_entry() {
        return type_entry;
    }

    public void setType_entry(String type_entry) {
        this.type_entry = type_entry;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public DocDet(String username, String type_entry, String entry) {
        this.username = username;
        this.type_entry = type_entry;
        this.entry = entry;
    }

    public DocDet() {
    }
}
