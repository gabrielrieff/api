package com.domain.coupon;

import java.util.Date;
import java.util.UUID;

import com.domain.event.Event;

public class Coupon {
    private UUID id;
    private String code;
    private Integer discount;
    private Date valid;

    private Event event;

    public Coupon(String code, Integer discount, Date valid, Event event){
        this.code = code;
        this.discount = discount;
        this.valid = valid;
        this.event = event;
    }

    public Coupon(){}

    public UUID getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Integer getDiscount() {
        return discount;
    }

    public Date getValid() {
        return valid;
    }

    public Event getEvent() {
        return event;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
