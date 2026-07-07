package com.application.implementations.usecases;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.interfaces.repositories.IAddressRepository;
import com.application.interfaces.usecases.IAddressUseCases;
import com.domain.address.Address;
import com.domain.event.Event;
import com.domain.event.dto.EventRequestDTO;

@Service
public class AddressUseCases implements IAddressUseCases{

    @Autowired private IAddressRepository repository;

    public Address create(EventRequestDTO data, Event event){
        Address address = new Address(data.city(), data.state(), event);
        return repository.save(address);
    };

    public Optional<Address> findByEventId(UUID eventId){
        return repository.findByEventId(eventId);
    }
}
