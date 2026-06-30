package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.domain.user.RegisterUserDTO;
import com.domain.user.User;
import com.domain.user.UserDetailDTO;
import com.repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserDetailDTO create(RegisterUserDTO data){

        if(this.userRepository.findByEmail(data.email()) != null) 
            throw new IllegalArgumentException("It was not possible to register the user.");

        var passwordEncriped = new BCryptPasswordEncoder().encode(data.password());

        var newUser = new User(
            data.name(),
            data.email(),
            passwordEncriped,
            data.role()
        );

        var user = this.userRepository.save(newUser);

        return new UserDetailDTO(user.getName(), user.getEmail(), user.getRole().toString());
    }
}
