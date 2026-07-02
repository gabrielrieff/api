package com.adapters.outbound.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adapters.outbound.entites.JpaCouponEntity;

public interface JpaCouponRepository extends JpaRepository<JpaCouponEntity, UUID>{
    List<JpaCouponEntity> findByEventIdAndValidAfter(UUID eventId, Date currentDate);
}
