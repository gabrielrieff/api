package com.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.adapters.outbound.entites.JpaAddressEntity;
import com.domain.address.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mapping(target = "event", ignore = true)
    Address jpaToDomain(JpaAddressEntity jpa);
}
