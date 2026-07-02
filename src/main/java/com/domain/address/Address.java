package com.domain.address;

import java.util.UUID;
import com.domain.event.Event;

public class Address {
    private UUID id;
    private String city;
    private String uf;

    private Event event;

    public Address(String city, String uf, Event event) {
        this.city = city;
        this.uf = uf;
        this.event = event;
    }

    public UUID getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getUf() {
        return uf;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public Event getEvent() {
        return event;
    }

}
