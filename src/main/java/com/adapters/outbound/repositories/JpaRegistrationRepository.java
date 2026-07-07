package com.adapters.outbound.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.adapters.outbound.entites.JpaRegistrationEntity;

public interface JpaRegistrationRepository  extends JpaRepository<JpaRegistrationEntity, UUID>{
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Registration r WHERE r.user.id = :userId AND r.event.id = :eventId")
    boolean existsByUserIdAndEventId(UUID userId, UUID eventId);

    @Query("SELECT COUNT(r) FROM Registration r WHERE r.event.id = :eventId AND r.status = 'CONFIRMED'")
    Integer countByEventId(UUID eventId);
}
