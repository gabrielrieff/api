package com.adapters.inbound.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import com.domain.user.AuthenticationDTO;
import com.domain.user.TokenResponseDTO;
import com.domain.user.User;
import com.infra.Security.TokenServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenServices tokenServices;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid AuthenticationDTO data){
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenServices.genereteToken((User)auth.getPrincipal());

        return ResponseEntity.ok(new TokenResponseDTO(token));
    }
}
