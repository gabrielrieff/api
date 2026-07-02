package com.domain.address;

import java.util.Optional;
import java.util.UUID;

public interface IAddressRepository {
    Address save(Address address);
    public Optional<Address> findByEventId(UUID eventId);
}
