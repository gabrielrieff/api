package com.application.interfaces.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.domain.coupon.Coupon;

public interface ICouponRepository {
    Coupon save(Coupon coupon);
    List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate);
}
