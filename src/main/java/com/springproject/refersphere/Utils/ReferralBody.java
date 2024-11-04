package com.springproject.refersphere.Utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReferralBody {

    private String position;
    private String company;
    private String experience;
    private String jobDescription;
    private Long ctc;
    private String skills;
    private String jobLink;
}
