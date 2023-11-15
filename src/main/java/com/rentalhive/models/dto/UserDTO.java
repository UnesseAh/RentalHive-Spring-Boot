package com.rentalhive.models.dto;

import com.rentalhive.models.entities.User;
import com.rentalhive.models.enums.Role;

public record UserDTO(
     String name,
     String email,
     String phoneNumber,
     String role
) {
    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRole().toString());
    }
    public User toUser() {
        return new User().builder()
                .name(this.name)
                .email(this.email)
                .phoneNumber(this.phoneNumber)
                .role(Role.valueOf(this.role))
                .build();
    }
}

