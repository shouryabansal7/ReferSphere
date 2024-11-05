package com.springproject.refersphere.repository;

import com.springproject.refersphere.model.AppliedReferral;
import com.springproject.refersphere.model.Referral;
import com.springproject.refersphere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReferralRepository extends JpaRepository<Referral,Long> {

    @Query(value = "SELECT * FROM referrals WHERE user_id = :user_id",nativeQuery = true)
    List<Referral> findAllApplicationsToPostsByUser(@Param("user_id") Long user_id);
}
