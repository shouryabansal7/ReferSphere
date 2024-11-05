package com.springproject.refersphere.repository;

import com.springproject.refersphere.model.AppliedReferral;
import com.springproject.refersphere.model.Referral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppliedReferralRepository extends JpaRepository<AppliedReferral,Long> {

    @Query(value = "SELECT * FROM applications WHERE user_id = :user_id",nativeQuery = true)
    List<AppliedReferral> findAllAppliedPostsByUserId(@Param("user_id") Long user_id);

    @Query(value = "SELECT * FROM applications WHERE user_id = :user_id AND post_id = :post_id",nativeQuery = true)
    AppliedReferral findPost(@Param("user_id") Long user_id,@Param("post_id") Long post_id);

}
