package com.domain.event;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.*;

public interface IEventRepository {
    Event save(Event event);
    Optional<Event> findById(UUID id);
    List<Event> findAll();
    void delete(UUID id);
    Page<Event> findUpcomingEvents(Date currentDate, Pageable pageable);
    Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate, Pageable pageable);
}
