package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.address.Address;

public interface IAddressRepository extends JpaRepository<Address, UUID>{

}
