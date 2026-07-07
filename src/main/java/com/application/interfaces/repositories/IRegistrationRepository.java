package com.application.interfaces.repositories;

import java.util.UUID;

import com.domain.registration.Registration;

public interface IRegistrationRepository {
    Registration save(Registration registration);
    boolean existsRegisterByUserId(UUID userId, UUID eventId);
    Integer countByEventId(UUID eventId);
}
