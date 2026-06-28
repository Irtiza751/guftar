package com.arbor.guftar.user.dto;

public record UpdateUserDto(
        String username,
        String bio,
        String imageUrl
) {
}
