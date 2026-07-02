package com.adapters.outbound.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.address.Address;

public interface AddressRepository extends JpaRepository<Address, UUID>{
    public Optional<Address> findByEventId(UUID eventId);
}
