package com.arbor.guftar.user.dto;

import com.arbor.guftar.user.types.AuthProvider;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record CreateUserRequest(
        @NotBlank(message = "username is required")
        String username,
        @NotBlank(message = "email is required")
        String email,
        String password,
        String phoneNo,
        String bio,
        @NotNull(message = "Auth method is required")
        AuthProvider authMethod
) {

    public CreateUserRequest {
        if(authMethod.equals(AuthProvider.LOCAL) && (password == null || password.isBlank())) {
            throw new IllegalArgumentException("Password is required field");
        }
    }
}
