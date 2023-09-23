package com.example.doctor.repository;

import com.example.doctor.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor,Integer> {


    @Query("SELECT u FROM Doctor u WHERE u.username = ?1")
    Doctor findByUsername(String username);

    Doctor save(Doctor doctor);

    @Query("SELECT u FROM Doctor u WHERE u.speciality = ?1")
    List<Doctor> findBySpeciality(String speciality);
}
