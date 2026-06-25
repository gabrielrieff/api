package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.coupon.Coupon;

public interface ICouponRepository extends JpaRepository<Coupon, UUID>{

}
