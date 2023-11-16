package com.rentalhive.models.dto;

import com.rentalhive.models.entities.User;
import com.rentalhive.models.enums.Role;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String name,
        String email,
        String phoneNumber,
        String role,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getId(),user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRole().toString(),user.getCreatedAt(),user.getModifiedAt());
    }
    public User toUser() {
        return new User().builder()
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .role(Role.valueOf(this.role))
                .createdAt(LocalDateTime.now())
                .build();
    }
}

