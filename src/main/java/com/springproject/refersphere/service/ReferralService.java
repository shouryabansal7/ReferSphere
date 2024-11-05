package com.springproject.refersphere.service;

import com.springproject.refersphere.Utils.ReferralBody;
import com.springproject.refersphere.Utils.ReferralPost;
import com.springproject.refersphere.Utils.ReferralResponse;
import com.springproject.refersphere.model.AppliedReferral;
import com.springproject.refersphere.model.Referral;
import com.springproject.refersphere.model.Status;
import com.springproject.refersphere.model.User;
import com.springproject.refersphere.repository.AppliedReferralRepository;
import com.springproject.refersphere.repository.ReferralRepository;
import com.springproject.refersphere.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReferralService {

    private final ReferralRepository referralRepository;
    private final UserRepository userRepository;
    private final AppliedReferralRepository appliedReferralRepository;


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

    public List<ReferralResponse> findApplied(){
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
        List<AppliedReferral> appliedReferrals = appliedReferralRepository.findAllAppliedPostsByUserId(userId);
        List<ReferralResponse> response = new ArrayList<>();
        appliedReferrals.forEach(appliedReferral -> {
            Optional<Referral> referral = referralRepository.findById(appliedReferral.getReferralId());
            ReferralResponse referralResponse = ReferralResponse
                    .builder()
                    .status(appliedReferral.getStatus())
                    .company(appliedReferral.getCompany())
                    .ctc(referral.get().getCtc())
                    .position(appliedReferral.getPosition())
                    .experience(referral.get().getExperience())
                    .jobDescription(referral.get().getJobDescription())
                    .jobLink(referral.get().getJobLink())
                    .build();
            response.add(referralResponse);
        });
        return response;
    }

    public String apply(Long id){
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
        Referral referral = referralRepository.getReferenceById(id);
        var application = AppliedReferral.builder()
                .company(referral.getCompany())
                .position(referral.getPosition())
                .referralId(referral.getId())
                .status(Status.IN_PROGRESS)
                .build();

        if(userId!=-1){
            if(userId==referral.getUserId()){
                return "Hello from secured endpoint, Referral cannot be applied for post id: "+id;
            }
            application.setUserId(userId);
        }
        var appliedReferral = appliedReferralRepository.save(application);
        return "Hello from secured endpoint, Referral applied for post id: "+id;
    }

    public ReferralPost findReferral(Long id) {
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

        Optional<Referral> referral = referralRepository.findById(id);
        ReferralPost referralPost = ReferralPost
                .builder()
                .company(referral.get().getCompany())
                .ctc(referral.get().getCtc())
                .position(referral.get().getPosition())
                .experience(referral.get().getExperience())
                .jobDescription(referral.get().getJobDescription())
                .jobLink(referral.get().getJobLink())
                .build();

        Boolean creator=false;
        if(referral.get().getUserId().equals(userId)){
            return referralPost;
        }

        AppliedReferral appliedReferral = appliedReferralRepository.findPost(userId,referral.get().getId());

        referralPost.setStatus(appliedReferral.getStatus().toString());

        return referralPost;
    }
}
