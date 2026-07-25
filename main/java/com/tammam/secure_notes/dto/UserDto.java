package com.tammam.secure_notes.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.tammam.secure_notes.models.Role;
import com.tammam.secure_notes.models.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String userName;
    private String email;
    private boolean accountNonLocked;
    private boolean accountNonExpired;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private LocalDate credentialsExpiryDate;
    private LocalDate accountExpiryDate;
    private String twoFactorSecret;
    private boolean isTwoFactorEnabled;
    private String signUpMethod;
    private Role role;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;


public static UserDto convertToDto(User user) {
    return new UserDto(
            user.getUserId(),
            user.getUserName(),
            user.getEmail(),
            user.isAccountNonLocked(),
            user.isAccountNonExpired(),
            user.isCredentilasNonExpired(),
            user.isEnabled(),
            user.getCrdentilasExpiryDate(),
            user.getAccountExpiryDate(),
            user.getTwoFactorSercret(),
            user.isIstwoFactorEnabled(),
            user.getSignUpMethod(),
            user.getRole(),
            user.getCreatedDate(),
            user.getUpdatedDate()
    );
}
}