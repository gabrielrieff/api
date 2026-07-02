package com.domain.coupon;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface ICouponRepository {
    Coupon save(Coupon coupon);
    List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate);
}
