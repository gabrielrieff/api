package com.application.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.usecases.ICouponUseCases;
import com.domain.coupon.Coupon;
import com.domain.coupon.ICouponRepository;
import com.domain.coupon.dto.CouponRequestDTO;
import com.domain.event.Event;
import com.domain.event.IEventRepository;

@Service
public class CouponService implements ICouponUseCases{
    @Autowired private ICouponRepository repository;
    @Autowired private IEventRepository _eventRepository;

    public Coupon createCoupon(UUID eventId, CouponRequestDTO data){

        Event event = this._eventRepository.findById(eventId)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        Coupon coupon = new Coupon(data.code(), data.discount(), new Date(data.valid()), event);
        return repository.save(coupon);
    }

    public List<Coupon> consultCoupons(UUID eventId, Date currentDate) {
        return this.repository.findByEventIdAndValidAfter(eventId, currentDate);
    }
}
