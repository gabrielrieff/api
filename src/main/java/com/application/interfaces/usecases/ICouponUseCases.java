package com.application.interfaces.usecases;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.domain.coupon.Coupon;
import com.domain.coupon.dto.CouponRequestDTO;

public interface ICouponUseCases {
    Coupon createCoupon(UUID eventId, CouponRequestDTO data);
    List<Coupon> consultCoupons(UUID eventId, Date currentDate);
}
