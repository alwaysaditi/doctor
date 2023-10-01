package com.example.doctor.repository;

import com.example.doctor.entity.Appointments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments,Integer> {

    Appointments save (Appointments appointments);

    @Query(nativeQuery = true, value = "SELECT * FROM Appointments a WHERE a.user_id = :docid AND a.day_week = :weekday")
    Appointments findAppointment(@Param("docid") String docid, @Param("weekday") String weekday);

    @Query("SELECT e FROM Appointments e WHERE e.user_id = :docid AND " +
            "(e.slot1 = :value OR e.slot2 = :value OR e.slot3 = :value OR e.slot4 = :value OR e.slot5 = :value OR e.slot6 = :value OR e.slot7 = :value OR e.slot8 = :value)")
    Appointments findDuplicate(@Param("docid") String docid, @Param("value") String value);



}
