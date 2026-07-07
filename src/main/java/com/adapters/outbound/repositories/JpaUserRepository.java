package com.adapters.outbound.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adapters.outbound.entites.JpaUserEntity;

public interface JpaUserRepository extends JpaRepository<JpaUserEntity, UUID>{
    JpaUserEntity findByEmail(String email);
}
