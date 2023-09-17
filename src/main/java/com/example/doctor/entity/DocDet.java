package com.example.doctor.entity;

import jakarta.persistence.*;

@Table(name = "doctors_det")
@Entity
public class DocDet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
  private int id;
    @Column(name="user_id")
    private String username;

    @Column(name = "type_entry")
    private String type_entry;

    @Column(name="entry")
    private String entry;

    public String getUsername() {
        return username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public DocDet(int id, String username, String type_entry, String entry) {
        this.id = id;
        this.username = username;
        this.type_entry = type_entry;
        this.entry = entry;
    }

    public DocDet() {
    }
}
