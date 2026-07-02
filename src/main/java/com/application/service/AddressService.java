package com.application.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.address.Address;
import com.domain.event.Event;
import com.domain.event.EventRequestDTO;
import com.adapters.outbound.entites.JpaEventEntity;
import com.adapters.outbound.repositories.AddressRepository;

@Service
public class AddressService {

    @Autowired
    private AddressRepository repository;

    public Address create(EventRequestDTO data, Event event){
        Address address = new Address();
        address.setCity(data.city());
        address.setUf(data.state());
        address.setEvent(new JpaEventEntity(event));

        return repository.save(address);
    };

    public Optional<Address> findByEventId(UUID eventId){
        return repository.findByEventId(eventId);
    }
}
