package com.arbor.guftar.user.service;

import com.arbor.guftar.user.dto.CreateUserRequest;
import com.arbor.guftar.user.dto.CreateUserResponse;
import com.arbor.guftar.user.dto.UserResponse;
import com.arbor.guftar.user.entity.User;
import com.arbor.guftar.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public CreateUserResponse create(CreateUserRequest userRequest) {
        User user = User.builder()
                .username(userRequest.username())
                .email(userRequest.email())
                .password(userRequest.password())
                .bio(userRequest.bio())
                .phoneNo(userRequest.phoneNo())
                .authMethod(userRequest.authMethod())
                .build();

        User savedUser = userRepository.save(user);
        return CreateUserResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .email(savedUser.getEmail())
                .isPrivateAccount(savedUser.getIsPrivateAccount())
                .isVerifiedEmail(savedUser.getIsVerifiedEmail())
                .isVerifiedPhone(savedUser.getIsVerifiedPhone())
                .build();
    }

    public List<UserResponse> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> UserResponse.builder()
                        .username(user.getUsername())
                        .email(user.getEmail())
                        .id(user.getId()).build())
                .toList();
    }
}
