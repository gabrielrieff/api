package com.application.implementations.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adapters.outbound.entites.JpaRegistrationEntity;
import com.adapters.outbound.repositories.JpaRegistrationRepository;
import com.application.interfaces.repositories.IRegistrationRepository;
import com.domain.registration.Registration;
import com.utils.mappers.RegistrationMapper;

@Repository
public class RegistrationRepository implements IRegistrationRepository {

    private final JpaRegistrationRepository jpaRegistrationRepository;
    @Autowired RegistrationMapper registrationMapper;

    public RegistrationRepository(JpaRegistrationRepository jpaRegistrationRepository) {
        this.jpaRegistrationRepository = jpaRegistrationRepository;
    }

    @Override
    public Registration save(Registration registration) {
        var jpaRegistration = new JpaRegistrationEntity(registration);
        jpaRegistration = this.jpaRegistrationRepository.save(jpaRegistration);
        return this.registrationMapper.jpaToDomain(jpaRegistration);
    }

    @Override
    public boolean existsRegisterByUserId(UUID userId, UUID eventId) {
        return this.jpaRegistrationRepository.existsByUserIdAndEventId(userId, eventId);
    }

    @Override
    public Integer countByEventId(UUID eventId) {
        return this.jpaRegistrationRepository.countByEventId(eventId);
    }

    @Override
    public Optional<Registration> findByUserIdAndEventId(UUID userId, UUID eventId) {
        var jpa = this.jpaRegistrationRepository.findByUserIdAndEventId(userId, eventId);
        if(jpa.isEmpty()) {
            return Optional.empty();
        }
        return Optional.ofNullable(registrationMapper.jpaToDomain(jpa.get()));
    }

}
