package com.domain.user;

import org.springframework.security.core.userdetails.UserDetails;

public interface IUserRepository {
    User save(User user);
    UserDetails findByEmail(String email);
}
