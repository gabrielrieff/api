package com.utils.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.adapters.outbound.entites.JpaCouponEntity;
import com.domain.coupon.Coupon;

@Mapper(componentModel = "spring")
public interface CouponMapper {
    @Mappings({
        @Mapping(target = "jpa.id", ignore = true),
        @Mapping(source = "jpa.discount", target = "discount"),
        @Mapping(source = "jpa.valid", target = "valid"),
    })
    Coupon toDomain(JpaCouponEntity jpa);
}
