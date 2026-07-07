package com.application.interfaces.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;

import com.domain.user.User;

public interface IUserRepository {
    User save(User user);
    Optional<User> findById(UUID id);
    UserDetails findByEmail(String email);

}
