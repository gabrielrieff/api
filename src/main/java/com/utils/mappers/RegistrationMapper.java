package com.utils.mappers;

import java.util.Date;
import java.util.Optional;

import org.mapstruct.Mapper;

import com.adapters.outbound.entites.JpaRegistrationEntity;
import com.domain.registration.Registration;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    Registration jpaToDomain(JpaRegistrationEntity jpa);

    default Optional<Date> map(Date date) {
        return Optional.ofNullable(date);
    }

    default Date map(Optional<Date> date) {
        return date != null ? date.orElse(null) : null;
    }
}