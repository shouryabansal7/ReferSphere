package com.springproject.refersphere.repository;

import com.springproject.refersphere.model.Referral;
import com.springproject.refersphere.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferralRepository extends JpaRepository<Referral,Long> {
}
