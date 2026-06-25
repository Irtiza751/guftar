package com.arbor.guftar.user.dto;

import com.arbor.guftar.user.types.AuthProvider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CreateUserResponse {

    private Long id;
    private String username;
    private String email;
    private AuthProvider authProvider;
    private Boolean isVerifiedEmail;
    private Boolean isVerifiedPhone;
    private Boolean isPrivateAccount;
}
