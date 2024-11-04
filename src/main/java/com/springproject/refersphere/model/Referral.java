package com.springproject.refersphere.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "referrals")
public class Referral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String position;
    private String company;
    private String experience;
    private String jobDescription;
    private Long ctc;
    private String skills;
    private String jobLink;
}
