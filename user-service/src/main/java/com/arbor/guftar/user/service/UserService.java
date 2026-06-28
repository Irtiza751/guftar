package com.arbor.guftar.user.service;

import com.arbor.guftar.common.dto.PaginatedResponse;
import com.arbor.guftar.common.dto.SuccessResponse;
import com.arbor.guftar.user.dto.CreateUserRequest;
import com.arbor.guftar.user.dto.CreateUserResponse;
import com.arbor.guftar.user.dto.UpdateUserDto;
import com.arbor.guftar.user.dto.UserResponse;
import com.arbor.guftar.user.entity.User;
import com.arbor.guftar.user.mappers.UserResponseMapper;
import com.arbor.guftar.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserResponseMapper userResponseMapper;

    @Transactional
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
                .bio(savedUser.getBio())
                .imageUrl(savedUser.getImageUrl())
                .isPrivateAccount(savedUser.getIsPrivateAccount())
                .isVerifiedEmail(savedUser.getIsVerifiedEmail())
                .isVerifiedPhone(savedUser.getIsVerifiedPhone())
                .createdAt(savedUser.getCreatedAt())
                .updatedAt(savedUser.getUpdatedAt())
                .build();
    }

    public PaginatedResponse<UserResponse> findAllUsers(Pageable pageable) {
        Page<UserResponse> pagedUsers = userRepository.findAll(pageable)
                .map(userResponseMapper::mapUserToUserResponse);

        return PaginatedResponse.from(pagedUsers);
    }

    public UserResponse findById(Long id) {
        return userRepository.findById(id)
                .map(userResponseMapper::mapUserToUserResponse)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Transactional
    public UserResponse updateUser(Long id, UpdateUserDto userRequest) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        userResponseMapper.updateUserDtoFields(userRequest, user);
        User savedUser = userRepository.save(user);
        return userResponseMapper.mapUserToUserResponse(savedUser);
    }

    public SuccessResponse deleteUser(Long id) {
        userRepository.deleteById(id);
        return new SuccessResponse(HttpStatus.OK.name(), "User with id: " + id + " has been deleted");
    }
}
