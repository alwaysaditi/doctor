package com.example.doctor.repository;

import com.example.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {


    @Query("SELECT u FROM Doctor u WHERE u.username = ?1")
    Doctor findByUsername(String username);

    Doctor save(Doctor doctor);
}
