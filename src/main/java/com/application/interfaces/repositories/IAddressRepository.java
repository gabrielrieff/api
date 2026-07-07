package com.application.interfaces.repositories;

import java.util.Optional;
import java.util.UUID;

import com.domain.address.Address;

public interface IAddressRepository {
    Address save(Address address);
    public Optional<Address> findByEventId(UUID eventId);
}
