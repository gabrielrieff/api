package com.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.domain.coupon.Coupon;
import com.domain.coupon.CouponRequestDTO;
import com.domain.event.Event;
import com.repositories.CouponRepository;
import com.repositories.EventRepository;

@Service
public class CouponService {
    @Autowired private CouponRepository repository;
    @Autowired private EventRepository eventRepository;

    public Coupon createCoupon(UUID eventId, CouponRequestDTO data){

        Event event = eventRepository.findById(eventId)
        .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        Coupon coupon = new Coupon();
        coupon.setCode(data.code());
        coupon.setDiscount(data.discount());
        coupon.setValid(new Date(data.valid()));
        coupon.setEvent(event);

        return repository.save(coupon);
    }

    public List<Coupon> consultCoupons(UUID eventId, Date currentDate) {
        return repository.findByEventIdAndValidAfter(eventId, currentDate);
    }
}
