package com.rentalhive.models.dto;

import com.rentalhive.models.entities.User;
import com.rentalhive.models.enums.Role;
import com.sun.istack.NotNull;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public record UserDTO(

        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email foramt is invalid")
        String email,
        @NotBlank(message = "Phone number is required")
        @Pattern(regexp = "^\\+?[0-9]{10,13}$", message = "Phone number format is invalid")
        String phoneNumber,
        @Pattern(regexp = "^(Agent|Manager|Client)$", message = "Role must be Agent,Manager or Client")
        String role,
        LocalDateTime createdAt,
        LocalDateTime modifiedAt
) {
    public static UserDTO fromUser(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.getPhoneNumber(), user.getRole().toString(),user.getCreatedAt(),user.getModifiedAt());
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

