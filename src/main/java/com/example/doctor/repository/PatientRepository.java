package com.example.doctor.repository;

import com.example.doctor.entity.Doctor;
import com.example.doctor.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {

    Patient save(Patient patient);

    @Query("SELECT u FROM Patient u WHERE u.user_id = ?1")
    Patient findByUsername(String username);
}
