package com.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.application.usecases.IUserUseCases;
import com.domain.user.IUserRepository;
import com.domain.user.User;
import com.domain.user.dto.RegisterUserDTO;
import com.domain.user.dto.UserDetailDTO;
import com.exception.NotFoundException;
import com.utils.mappers.UserMapper;

@Service
public class UserService implements IUserUseCases{
    @Autowired private IUserRepository userRepository;
    @Autowired private UserMapper _userMapper;

    public UserDetailDTO create(RegisterUserDTO data){

        if(this.userRepository.findByEmail(data.email()) != null) 
            throw new NotFoundException("It was not possible to register the user.");

        var passwordEncriped = new BCryptPasswordEncoder().encode(data.password());

        var newUser = new User(data.name(), data.email(), passwordEncriped, data.role());

        var user = this.userRepository.save(newUser);

        return _userMapper.domainToDetailDTO(user);
    }
}
