package com.adapters.outbound.entites;

import java.util.Date;
import java.util.UUID;

import com.domain.coupon.Coupon;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Coupon")
@Table(name = "coupon")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaCouponEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private Integer discount;
    private Date valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private JpaEventEntity event;

    public JpaCouponEntity(Coupon coupon) {
        this.id = coupon.getId();
        this.code = coupon.getCode();
        this.discount = coupon.getDiscount();
        this.valid = coupon.getValid();

        this.event = new JpaEventEntity(coupon.getEvent());
    }
}
