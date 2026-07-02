package com.application.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.usecases.IEventUseCases;
import com.domain.address.Address;
import com.domain.coupon.Coupon;
import com.domain.event.*;
import com.utils.mappers.EventMapper;

@Service
public class EventService implements IEventUseCases {

    
    private IEventRepository eventRepository;
    @Autowired
    private EventMapper eventMapper;
    @Autowired
    private AddressService addressService;
    @Autowired
    private CouponService couponService;

    public Event createEvent(EventRequestDTO data){
        String imgUrl = "";

        if(data.image() != null){
            imgUrl = this.UploadImg(data.image());
        }

        Event event = new Event(null, data.title(), data.description(), imgUrl, data.eventUrl(), data.remote(), new Date(data.date()));
        event = eventRepository.save(event);

        if(!data.remote())
            this.addressService.create(data, event);

        return event;
    }

    public List<EventResponseDTO> getEvents(int page, int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize);
        List<EventResponseDTO> eventsPage = this.eventRepository
            .findUpcomingEvents(new Date(), pageable)
            .map(event -> eventMapper.domainToResponseDTO(event, event.getAddress()))
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
        Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        
        List<Coupon> coupons = couponService.consultCoupons(eventId, new Date());
        Optional<Address> address = addressService.findByEventId(eventId);

        return eventMapper.domainToDetailsDTO(event, address, coupons);
    }

    private String UploadImg(MultipartFile file){
        return "https://shre.ink/31c7";
    }
}
