package com.application.implementations.repositories;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.adapters.outbound.entites.JpaCouponEntity;
import com.adapters.outbound.repositories.JpaCouponRepository;
import com.application.interfaces.repositories.ICouponRepository;
import com.domain.coupon.Coupon;
import com.utils.mappers.CouponMapper;

@Repository
public class CouponRepository implements ICouponRepository{
    final private JpaCouponRepository _jpaCouponRepository;

    public CouponRepository(JpaCouponRepository jpaCouponRepository) {
        this._jpaCouponRepository = jpaCouponRepository;
    }

    @Autowired private CouponMapper _couponMapper;

    @Override
    public Coupon save(Coupon coupon) {
        var jpaCoupon = new JpaCouponEntity(coupon);
        _jpaCouponRepository.save(jpaCoupon);
        return _couponMapper.toDomain(jpaCoupon);
    }

    @Override
    public List<Coupon> findByEventIdAndValidAfter(UUID eventId, Date currentDate) {
        List<JpaCouponEntity> jpaCoupons = _jpaCouponRepository.findByEventIdAndValidAfter(eventId, currentDate);
        return jpaCoupons.stream().map(c -> _couponMapper.toDomain(c)).toList();
    }
    
}
