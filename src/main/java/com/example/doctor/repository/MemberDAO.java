package com.example.doctor.repository;

import com.example.doctor.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberDAO extends JpaRepository<Member,String> {

    Member save(Member member);

   Member findByUserName(String userName);

    @Query(nativeQuery= true, value="SELECT user_id FROM members WHERE user_id = :userid")
    List<String> findByUsername(String userid);

    @Query(nativeQuery= true, value="SELECT email FROM members WHERE user_id = :userid")
   String findEmailByUsername(String userid);

}
