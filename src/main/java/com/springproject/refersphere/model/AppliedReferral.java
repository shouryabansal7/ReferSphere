package com.springproject.refersphere.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "applications")
public class AppliedReferral {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long userId;
    private Long referralId;
    private String position;
    private String company;
    @Enumerated(EnumType.STRING)
    private Status status;
}
