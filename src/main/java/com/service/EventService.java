package com.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.domain.address.Address;
import com.domain.coupon.Coupon;
import com.domain.event.*;
import com.repositories.EventRepository;

@Service
public class EventService {
    @Autowired
    private EventRepository repository;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CouponService couponService;

    public Event createEvent(EventRequestDTO data){
        String imgUrl = "";

        if(data.image() != null){
            imgUrl = this.UploadImg(data.image());
        }

        Event event = new Event();
        event.setTitle(data.title());
        event.setDescription(data.description());
        event.setEventUrl(data.eventUrl());
        event.setDate(new Date(data.date()));
        event.setImgUrl(imgUrl);
        event.setRemote(data.remote());

        repository.save(event);

        if(!data.remote())
            this.addressService.create(data, event);

        return event;
    }

    public List<EventResponseDTO> getEvents(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        List<EventResponseDTO> eventsPage = this.repository
            .findUpcomingEvents(new Date(), pageable)
            .map(event -> 
                new EventResponseDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getDate(),
                        event.getAddress() != null ? event.getAddress().getCity() : "",
                        event.getAddress()!= null ? event.getAddress().getUf() : "",
                        event.getRemote(),
                        event.getEventUrl(),
                        event.getImgUrl()
                    )
                )
                .stream().toList();
        return eventsPage;
    }

    public List<EventResponseDTO> getFilteredEvents(int page, int pageSize, String city, String uf, Date startDate, Date endDate){
        city = (city != null) ? city : "";
        uf = (uf != null) ? uf : "";
        startDate = (startDate != null) ? startDate : new Date(0);
        endDate = (endDate != null) ? endDate : new Date();

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<EventAddressProjection> eventsPage = this.repository.findFilteredEvents(city, uf, startDate, endDate, pageable);
        return eventsPage.map(event -> 
                    new EventResponseDTO(
                        event.getId(),
                        event.getTitle(),
                        event.getDescription(),
                        event.getDate(),
                        event.getCity() != null ? event.getCity() : "",
                        event.getUf() != null ? event.getUf() : "",
                        event.getRemote(),
                        event.getEventUrl(),
                        event.getImgUrl()
                    )
                )
                .stream().toList();
    }

    public EventDetailsDTO getEventDetails(UUID eventId){
        Event event = repository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        List<Coupon> coupons = couponService.consultCoupons(eventId, new Date());
        Optional<Address> address = addressService.findByEventId(eventId);

        List<EventDetailsDTO.CouponDTO> couponDTOs = coupons.stream()
                .map(coupon -> new EventDetailsDTO.CouponDTO(
                        coupon.getCode(),
                        coupon.getDiscount(),
                        coupon.getValid()))
                .collect(Collectors.toList());

        return new EventDetailsDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                address.isPresent() ? address.get().getCity() : "",
                address.isPresent() ? address.get().getUf() : "",
                event.getImgUrl(),
                event.getEventUrl(),
                couponDTOs);
    }

    private String UploadImg(MultipartFile file){
        return "https://shre.ink/31c7";
    }
}
