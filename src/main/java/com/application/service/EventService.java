package com.application.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.usecases.IAddressUseCases;
import com.application.usecases.IEventUseCases;
import com.domain.address.Address;
import com.domain.coupon.Coupon;
import com.domain.event.*;
import com.domain.event.dto.EventAddressProjection;
import com.domain.event.dto.EventDetailsDTO;
import com.domain.event.dto.EventRequestDTO;
import com.domain.event.dto.EventResponseDTO;
import com.utils.mappers.EventMapper;

@Service
public class EventService implements IEventUseCases {

    @Autowired private IEventRepository eventRepository;
    @Autowired private EventMapper eventMapper;
    @Autowired private IAddressUseCases addressUseCases;
    @Autowired private CouponService couponService;

    public Event createEvent(EventRequestDTO data){
        String imgUrl = "";

        if(data.image() != null){
            imgUrl = this.UploadImg(data.image());
        }

        Event event = new Event(data.title(), data.description(), imgUrl, data.eventUrl(), data.remote(), new Date(data.date()));
        event = this.eventRepository.save(event);

        if(!data.remote())
            this.addressUseCases.create(data, event);

        return event;
    }

    public List<EventResponseDTO> getEvents(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        List<EventResponseDTO> eventsPage = this.eventRepository
            .findUpcomingEvents(new Date(), pageable)
            .map(event -> 
                    eventMapper.domainToResponseDTO(event, event.getAddress()))
                .stream().toList();
        return eventsPage;
    }

    public List<EventResponseDTO> getFilteredEvents(int page, int pageSize, String city, String uf, Date startDate, Date endDate){
        city = (city != null) ? city : "";
        uf = (uf != null) ? uf : "";
        startDate = (startDate != null) ? startDate : new Date(0);
        endDate = (endDate != null) ? endDate : new Date();

        Pageable pageable = PageRequest.of(page, pageSize);

        Page<EventAddressProjection> eventsPage = this.eventRepository.findFilteredEvents(city, uf, startDate, endDate, pageable);
        return eventsPage.map(event -> 
                eventMapper.eventAddressProjectionToResponseDTO(event))
                .stream().toList();
    }

    public EventDetailsDTO getEventDetails(UUID eventId){
        Event event = this.eventRepository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        List<Coupon> coupons = this.couponService.consultCoupons(eventId, new Date());
        Optional<Address> address = this.addressUseCases.findByEventId(eventId);

        return eventMapper.domainToDetailsDTO(event, address, coupons);
    }

    private String UploadImg(MultipartFile file){
        return "https://shre.ink/31c7";
    }
}
