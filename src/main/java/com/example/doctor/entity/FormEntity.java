package com.example.doctor.entity;

public class FormEntity {

    private Doctor doctor;

    private DocDet docdet;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DocDet getDocdet() {
        return docdet;
    }

    public void setDocdet(DocDet docdet) {
        this.docdet = docdet;
    }

    public FormEntity() {
    }
}
