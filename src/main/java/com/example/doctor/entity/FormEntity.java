package com.example.doctor.entity;

public class FormEntity {

    private Doctor doctor;

    private DocDet docdet1;

    private DocDet docdet2;

    private DocDet docdet3;

   private DocDet docdet4;

    public FormEntity(Doctor doctor, DocDet docdet1, DocDet docdet2, DocDet docdet3, DocDet docdet4) {
        this.doctor = doctor;
        this.docdet1 = docdet1;
        this.docdet2 = docdet2;
        this.docdet3 = docdet3;
        this.docdet4 = docdet4;
    }

    public FormEntity() {
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public DocDet getDocdet1() {
        return docdet1;
    }

    public void setDocdet1(DocDet docdet1) {
        this.docdet1 = docdet1;
    }

    public DocDet getDocdet2() {
        return docdet2;
    }

    public void setDocdet2(DocDet docdet2) {
        this.docdet2 = docdet2;
    }

    public DocDet getDocdet3() {
        return docdet3;
    }

    public void setDocdet3(DocDet docdet3) {
        this.docdet3 = docdet3;
    }

    public DocDet getDocdet4() {
        return docdet4;
    }

    public void setDocdet4(DocDet docdet4) {
        this.docdet4 = docdet4;
    }
}
