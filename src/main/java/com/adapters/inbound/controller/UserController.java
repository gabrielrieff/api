package com.adapters.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.application.interfaces.usecases.IUserUseCases;
import com.domain.user.dto.RegisterUserDTO;
import com.domain.user.dto.UserDetailDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired private IUserUseCases _userUseCases;
    
    @PostMapping()
    public ResponseEntity<UserDetailDTO> create(@RequestBody @Valid RegisterUserDTO data){
        var response = this._userUseCases.create(data);
        return ResponseEntity.ok(response);
    }
}
