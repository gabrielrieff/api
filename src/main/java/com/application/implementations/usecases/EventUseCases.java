package com.application.implementations.usecases;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.application.interfaces.repositories.IEventRepository;
import com.application.interfaces.repositories.IRegistrationRepository;
import com.application.interfaces.repositories.IUserRepository;
import com.application.interfaces.services.IUploadSerivece;
import com.application.interfaces.usecases.IAddressUseCases;
import com.application.interfaces.usecases.IEventUseCases;
import com.domain.address.Address;
import com.domain.coupon.Coupon;
import com.domain.event.*;
import com.domain.event.dto.EventAddressProjection;
import com.domain.event.dto.EventDetailsDTO;
import com.domain.event.dto.EventRequestDTO;
import com.domain.event.dto.EventResponseDTO;
import com.domain.registration.Registration;
import com.exception.GenericException;
import com.exception.NotFoundException;
import com.utils.mappers.EventMapper;

import jakarta.transaction.Transactional;

@Service
public class EventUseCases implements IEventUseCases {

    @Autowired private IEventRepository eventRepository;
    @Autowired private IUserRepository userRepository;
    @Autowired private IRegistrationRepository registrationRepository;
    @Autowired private IUploadSerivece uploadService;
    @Autowired private EventMapper eventMapper;
    @Autowired private IAddressUseCases addressUseCases;
    @Autowired private CouponUseCases couponService;

    public Event createEvent(EventRequestDTO data){
        String imgUrl = "";

        if(data.image() != null){
            imgUrl = this.uploadService.uploadImage(data.image());
        }

        Event event = new Event(data.title(), data.description(), imgUrl, data.eventUrl(), data.remote(), new Date(data.date()), data.maxParticipants());
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
            .orElseThrow(() -> new NotFoundException("Event not found"));
        
        List<Coupon> coupons = this.couponService.consultCoupons(eventId, new Date());
        Optional<Address> address = this.addressUseCases.findByEventId(eventId);

        return eventMapper.domainToDetailsDTO(event, address, coupons);
    }
    
    @Transactional
    public void registerUserInEvent(UUID eventId, UUID userId) {
        var user = this.userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));

        var event = this.eventRepository.findById(eventId)
            .orElseThrow(() -> new NotFoundException("Event not found"));

        if(event.getDate().before(new Date())){
            throw new GenericException("Event has already occurred", HttpStatus.BAD_REQUEST, "EVENT_ALREADY_OCCURRED");
        }

        var quantityRegistrations = this.registrationRepository.countByEventId(eventId);

        if(event.getMaxParticipants() != null && quantityRegistrations >= event.getMaxParticipants()){
            throw new GenericException("Event is full", HttpStatus.BAD_REQUEST, "EVENT_FULL");
        }

        if(this.registrationRepository.existsRegisterByUserId(userId, eventId)){
            throw new GenericException("User is already registered for this event", HttpStatus.BAD_REQUEST, "USER_ALREADY_REGISTERED");
        }

        event.incrementConfirmedParticipants();

        var registration = new Registration(user, event);
        this.eventRepository.save(event);
        this.registrationRepository.save(registration);
    }
}
