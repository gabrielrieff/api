package com.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.domain.coupon.Coupon;
import com.domain.coupon.CouponRequestDTO;
import com.service.CouponService;

@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    @Autowired
    private CouponService couponService;

    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> createCoupon(@PathVariable UUID eventId, @RequestBody CouponRequestDTO body){
        Coupon coupon = this.couponService.createCoupon(eventId, body);
        return ResponseEntity.ok(coupon);
    }
}
