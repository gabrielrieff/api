package com.application.implementations.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adapters.outbound.entites.JpaAddressEntity;
import com.adapters.outbound.repositories.JpaAddressRepository;
import com.application.interfaces.repositories.IAddressRepository;
import com.domain.address.Address;
import com.utils.mappers.AddressMapper;

@Repository
public class AddressRepository implements IAddressRepository{
    final private JpaAddressRepository _jpaAddressRepository;

    public AddressRepository(JpaAddressRepository jpaAddressRepository) {
        this._jpaAddressRepository = jpaAddressRepository;
    }

    @Autowired private AddressMapper _addressMapper;

    @Override
    public Address save(Address address) {
        var jpaAddress = new JpaAddressEntity(address);
        _jpaAddressRepository.save(jpaAddress);
        return _addressMapper.toEntity(jpaAddress);
    }

    @Override
    public Optional<Address> findByEventId(UUID eventId) {
        return _jpaAddressRepository.findByEventId(eventId);
    }

}
