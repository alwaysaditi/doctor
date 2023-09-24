package com.example.doctor.repository;

import com.example.doctor.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments,Integer> {

    Appointments save (Appointments appointments);
}
