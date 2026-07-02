package com.adapters.outbound.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adapters.outbound.entites.JpaAddressEntity;
import com.domain.address.Address;

public interface JpaAddressRepository extends JpaRepository<JpaAddressEntity, UUID>{
    public Optional<Address> findByEventId(UUID eventId);
}
