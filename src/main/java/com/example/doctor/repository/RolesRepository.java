package com.example.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.doctor.entity.Roles;
public interface RolesRepository extends JpaRepository<Roles,String> {
    Roles save(Roles role);
}
