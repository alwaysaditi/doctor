package com.example.doctor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.doctor.entity.Roles;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles,String> {
    Roles save(Roles role);

    @Query(nativeQuery= true, value="SELECT * FROM roles WHERE role = 'ROLE_USER'")
    List<Roles> user_roles();
}
