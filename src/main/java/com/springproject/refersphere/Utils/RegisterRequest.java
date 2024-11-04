package com.springproject.refersphere.Utils;

import com.springproject.refersphere.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String username;
    private String password;
    private String name;
    private String phoneNo;
    private String email;
    private Role role;
}