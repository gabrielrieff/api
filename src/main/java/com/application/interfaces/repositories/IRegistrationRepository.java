package com.application.interfaces.repositories;

import java.util.Optional;
import java.util.UUID;

import com.domain.registration.Registration;

public interface IRegistrationRepository {
    Registration save(Registration registration);
    Optional<Registration> findByUserIdAndEventId(UUID userId, UUID eventId);
    boolean existsRegisterByUserId(UUID userId, UUID eventId);
    Integer countByEventId(UUID eventId);
}
