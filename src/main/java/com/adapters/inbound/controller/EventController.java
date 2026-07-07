package com.adapters.inbound.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.application.implementations.usecases.EventUseCases;
import com.domain.event.Event;
import com.domain.event.dto.EventDetailsDTO;
import com.domain.event.dto.EventRequestDTO;
import com.domain.event.dto.EventResponseDTO;
import com.domain.user.User;
import com.infra.Security.LoggedUser;

@RestController
@RequestMapping("/api/event")
public class EventController {
    @Autowired
    private EventUseCases eventService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Event> create(@RequestParam("title") String title,
                                        @RequestParam(value = "description", required = false) String description,
                                        @RequestParam("date") Long date,
                                        @RequestParam("city") String city,
                                        @RequestParam("state") String state,
                                        @RequestParam("remote") Boolean remote,
                                        @RequestParam("eventUrl") String eventUrl,
                                        @RequestParam("maxParticipants") Integer maxParticipants,
                                        @RequestParam(value = "image", required = false) MultipartFile image){
        EventRequestDTO dto = new EventRequestDTO(title, description, date, city, state, remote, eventUrl, maxParticipants, image);
        Event event = this.eventService.createEvent(dto);
        return ResponseEntity.ok(event);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> getEvents(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize){
        
        List<EventResponseDTO> response = this.eventService.getEvents(page, pageSize);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<EventResponseDTO>> filterEvent(@RequestParam(defaultValue = "0") int page,
                                                                @RequestParam(defaultValue = "10") int pageSize,
                                                                @RequestParam(required = false) String city,
                                                                @RequestParam(required = false) String uf,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
                                                                @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        
        List<EventResponseDTO> response = this.eventService.getFilteredEvents(page, pageSize, city, uf, startDate, endDate);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventDetailsDTO> getEventDetails(@PathVariable UUID eventId){
        
        EventDetailsDTO response = this.eventService.getEventDetails(eventId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{eventId}/registrations")
    public ResponseEntity<Void> registrationEvent(@PathVariable UUID eventId, @LoggedUser User user){
        this.eventService.registerUserInEvent(eventId, user.getId());
        return ResponseEntity.ok().build();
    }
}
