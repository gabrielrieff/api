package com.adapters.outbound.repositories;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.coupon.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, UUID>{
    List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate);
}
