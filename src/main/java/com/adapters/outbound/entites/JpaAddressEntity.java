package com.adapters.outbound.entites;

import java.util.UUID;

import com.domain.address.Address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Address")
@Table(name = "address")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaAddressEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String city;
    private String uf;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private JpaEventEntity event;

    public JpaAddressEntity(Address address){
        this.id = address.getId();
        this.city = address.getCity();
        this.uf = address.getUf();
    }
}
