package com.domain.event;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.domain.address.Address;

public class Event {
    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private Date date;

    private Address address;

    public Event(String title, String description, String imgUrl, String eventUrl, Boolean remote, Date date){
        this.title = title;
        this.description = description;
        this.imgUrl = imgUrl;
        this.eventUrl = eventUrl;
        this.remote = remote;
        this.date = date;
    };

    public Event(){};

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getEventUrl() {
        return eventUrl;
    }
    
    public Boolean getRemote() {
        return remote;
    }

    public Date getDate() {
        return date;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }
    
    public void setRemote(Boolean remote) {
        this.remote = remote;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
