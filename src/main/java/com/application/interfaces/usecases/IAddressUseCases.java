package com.application.interfaces.usecases;

import java.util.Optional;
import java.util.UUID;

import com.domain.address.Address;
import com.domain.event.Event;
import com.domain.event.dto.EventRequestDTO;

public interface IAddressUseCases {
    Address create(EventRequestDTO data, Event event);
    Optional<Address> findByEventId(UUID eventId);
}
