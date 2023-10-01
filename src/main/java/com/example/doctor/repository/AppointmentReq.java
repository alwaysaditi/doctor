package com.example.doctor.repository;

import com.example.doctor.entity.ApptRequest;
import com.example.doctor.entity.DocDet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentReq extends JpaRepository<ApptRequest,Integer> {

ApptRequest save(ApptRequest apptRequest);

    @Query(nativeQuery = true,value="SELECT * FROM a_requests e WHERE e.doc_id = :userId AND e.status =:status")
    List<ApptRequest> findApptReqs(@Param("userId") String userId, @Param("status") String status);
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "DELETE from a_requests e where e.doc_id =:doc_id AND e.user_id =:user_id and e.status =:status")
    Integer removePending(@Param("doc_id") String doc_id,@Param("user_id") String user_id, @Param("status") String status);
}
