package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.domain.user.RegisterUserDTO;
import com.domain.user.UserDetailDTO;
import com.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    
    @PostMapping()
    public ResponseEntity<UserDetailDTO> create(@RequestBody @Valid RegisterUserDTO data){
        var response = this.userService.create(data);
        return ResponseEntity.ok(response);
    }
}
