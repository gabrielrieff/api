package com.adapters.outbound.repositories.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.adapters.outbound.entites.JpaUserEntity;
import com.adapters.outbound.repositories.JpaUserRepository;
import com.domain.user.IUserRepository;
import com.domain.user.User;
import com.utils.mappers.UserMapper;

@Repository
public class UserRepository implements IUserRepository {

    final private JpaUserRepository _jpaUserRepository;

    public UserRepository(JpaUserRepository jpaEventRepository) {
        this._jpaUserRepository = jpaEventRepository;
    }

    @Autowired private UserMapper _userMapper;

    @Override
    public User save(User user) {
        var jpaUser = new JpaUserEntity(user);
        _jpaUserRepository.save(jpaUser);
        return _userMapper.jpaToDomain(jpaUser);
    }

    @Override
    public UserDetails findByEmail(String email) {
        return _jpaUserRepository.findByEmail(email);
    }
    
}
