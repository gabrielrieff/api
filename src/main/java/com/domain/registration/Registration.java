package com.domain.registration;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.domain.Enum.RegistrationStatus;
import com.domain.event.Event;
import com.domain.user.User;

public class Registration {
    private UUID id;
    private Date registeredAt;
    private Optional<Date> cancelledAt;
    private Date createdAt;
    private Date updatedAt;
    private User user;
    private Event event;
    private RegistrationStatus status;

    public Registration(User user, Event event){
        this.user = user;
        this.event = event;
        this.registeredAt = new Date();
        this.createdAt = new Date();
        this.updatedAt = new Date();
        this.status = RegistrationStatus.CONFIRMED;
    }

    public Registration(){};

    public UUID getId() {
        return id;
    }

    public Date getRegisteredAt() {
        return registeredAt;
    }

    public Optional<Date> getCancelledAt() {
        return cancelledAt;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    public void cancel(){
        this.status = RegistrationStatus.CANCELLED;
        this.cancelledAt = Optional.of(new Date());
        this.updatedAt = new Date();
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setRegisteredAt(Date registeredAt) {
        this.registeredAt = registeredAt;
    }

    public void setCancelledAt(Optional<Date> cancelledAt) {
        this.cancelledAt = cancelledAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setStatus(RegistrationStatus status) {
        this.status = status;
    }
}
