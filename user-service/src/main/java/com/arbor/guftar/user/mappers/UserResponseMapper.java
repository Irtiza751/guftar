package com.arbor.guftar.user.mappers;

import com.arbor.guftar.user.dto.UpdateUserDto;
import com.arbor.guftar.user.dto.UserResponse;
import com.arbor.guftar.user.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper {

    public UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .bio(user.getBio())
                .imageUrl(user.getImageUrl())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }

    public void updateUserDtoFields(UpdateUserDto userRequest, User user) {
        if(userRequest.username() != null && !userRequest.username().isBlank()) {
            user.setUsername(userRequest.username());
        } else if(userRequest.bio() != null && !userRequest.bio().isBlank()) {
            user.setBio(userRequest.bio());
        }
    }
}
