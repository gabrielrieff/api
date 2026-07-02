package com.domain.user.dto;

import com.domain.Enum.UserRole;

public record RegisterUserDTO(String name, String email, String password, UserRole role) {
    
}
