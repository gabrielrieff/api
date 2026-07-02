package com.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.adapters.outbound.entites.JpaAddressEntity;
import com.domain.address.Address;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    @Mappings({
        @Mapping(target = "jpa.id", ignore = true),
        @Mapping(source = "jpa.city", target = "city"),
        @Mapping(source = "jpa.uf", target = "uf"),
        @Mapping(source = "jpa.event", target = "event"),
    })
    Address toEntity(JpaAddressEntity jpa);
}
