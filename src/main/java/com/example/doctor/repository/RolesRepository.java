package com.example.doctor.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.doctor.entity.Roles;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RolesRepository extends JpaRepository<Roles,String> {
    Roles save(Roles role);

    @Query(nativeQuery= true, value="SELECT * FROM roles WHERE role = 'ROLE_USER'")
    List<Roles> user_roles();

//    @Transactional
//    @Query("UPDATE Roles u SET u.role = :newRole WHERE u.user_id = :userId")
//    void updateUserRole(@Param("userId") String userId, @Param("newRole") String newRole);

}
