package com.application.implementations.usecases;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.interfaces.repositories.ICouponRepository;
import com.application.interfaces.repositories.IEventRepository;
import com.application.interfaces.usecases.ICouponUseCases;
import com.domain.coupon.Coupon;
import com.domain.coupon.dto.CouponRequestDTO;
import com.domain.event.Event;

@Service
public class CouponUseCases implements ICouponUseCases{
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
