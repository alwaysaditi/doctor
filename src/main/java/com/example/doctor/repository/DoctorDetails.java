package com.example.doctor.repository;

import com.example.doctor.entity.DocDet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDetails extends JpaRepository<DocDet,String> {

    DocDet save(DocDet docDet);
}
