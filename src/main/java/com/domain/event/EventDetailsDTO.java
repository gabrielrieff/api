package com.domain.event;

import java.util.*;

public record EventDetailsDTO(
    UUID id,
    String title,
    String description,
    Date date,
    String city,
    String state,
    String imgUrl,
    String eventUrl,
    List<CouponDTO> coupons) {

public record CouponDTO(
        String code,
        Integer discount,
        Date valid) {
}
}