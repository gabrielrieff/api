package com.application.interfaces.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.*;
import com.domain.event.Event;
import com.domain.event.dto.EventAddressProjection;

public interface IEventRepository {
    Event save(Event event);
    Optional<Event> findById(UUID id);
    List<Event> findAll();
    void delete(UUID id);
    Page<Event> findUpcomingEvents(Date currentDate, Pageable pageable);
    Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate, Pageable pageable);
    Optional<Event> findByIdForUpdate(UUID id);
}
