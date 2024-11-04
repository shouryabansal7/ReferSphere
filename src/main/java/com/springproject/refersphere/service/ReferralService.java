package com.springproject.refersphere.service;

import com.springproject.refersphere.Utils.ReferralBody;
import com.springproject.refersphere.model.Referral;
import com.springproject.refersphere.model.User;
import com.springproject.refersphere.repository.ReferralRepository;
import com.springproject.refersphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReferralService {

    private final ReferralRepository referralRepository;
    private final UserRepository userRepository;


    public void create(ReferralBody referralBody){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = (long) -1;
        if (authentication != null){
            Object principal = authentication.getPrincipal();
            if (principal instanceof UserDetails) {
                // If the principal is of type UserDetails, we can extract the username
                String username = ((UserDetails) principal).getUsername();
                Optional<User> user = userRepository.findByUsername(username);
                if (user.isPresent()) {
                    userId = user.get().getId();
                }
            }
        }
        var referral = Referral.builder()
                .position(referralBody.getPosition())
                .ctc(referralBody.getCtc())
                .company(referralBody.getCompany())
                .experience(referralBody.getExperience())
                .jobDescription(referralBody.getJobDescription())
                .skills(referralBody.getSkills())
                .jobLink(referralBody.getJobLink())
                .build();

        if(userId!=-1){
            referral.setUserId(userId);
        }
        var createdPost = referralRepository.save(referral);
        return;
    }

    public List<Referral> findAll() {
        return referralRepository.findAll();
    }
}
