package com.example.doctor.repository;

import com.example.doctor.entity.DocDet;
import com.example.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DoctorDetails extends JpaRepository<DocDet,String> {

    DocDet save(DocDet docDet);

    @Query("SELECT u FROM DocDet u WHERE u.username = ?1")
    DocDet findByUsername(String username);


    @Query("SELECT e FROM DocDet e WHERE e.username = :userId")
    List<DocDet> findByUserId(@Param("userId") String userId);
}
