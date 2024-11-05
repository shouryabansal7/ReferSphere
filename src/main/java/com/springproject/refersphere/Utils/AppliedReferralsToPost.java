package com.springproject.refersphere.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppliedReferralsToPost {

    private String name;
    private String phoneNo;
    private String email;
    private String linkedIn;
    private String resume;
    private String skills;
    private String position;
    private String company;
    private String experience;
    private String jobDescription;
    private String jobLink;
    private String status;

}
