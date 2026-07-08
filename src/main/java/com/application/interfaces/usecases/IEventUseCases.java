package com.application.interfaces.usecases;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.domain.event.Event;
import com.domain.event.dto.EventDetailsDTO;
import com.domain.event.dto.EventRequestDTO;
import com.domain.event.dto.EventResponseDTO;

public interface IEventUseCases {
    public Event createEvent(EventRequestDTO eventRequestDTO);
    public List<EventResponseDTO> getEvents(int page, int pageSize);
    public List<EventResponseDTO> getFilteredEvents(int page, int pageSize, String city, String uf, Date startDate, Date endDate);
    public EventDetailsDTO getEventDetails(UUID eventId);
    public void registerUserInEvent(UUID eventId, UUID userId);
    public void cancelEventRegistration(UUID eventId, UUID userId);
}
