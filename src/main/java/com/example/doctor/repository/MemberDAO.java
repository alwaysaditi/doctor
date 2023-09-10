package com.example.doctor.repository;

import com.example.doctor.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member,String> {

    Member save(Member member);


}
