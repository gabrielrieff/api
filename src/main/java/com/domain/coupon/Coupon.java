package com.domain.coupon;

import java.util.Date;
import java.util.UUID;

import com.adapters.outbound.entites.JpaEventEntity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "coupon")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {
    @Id
    @GeneratedValue
    private UUID id;

    private String code;
    private Integer discount;
    private Date valid;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private JpaEventEntity event;
}
