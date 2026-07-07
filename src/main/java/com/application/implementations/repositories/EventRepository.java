package com.application.implementations.repositories;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import com.adapters.outbound.entites.JpaEventEntity;
import com.adapters.outbound.repositories.JpaEventRepository;
import com.application.interfaces.repositories.IEventRepository;
import com.domain.event.Event;
import com.domain.event.dto.EventAddressProjection;
import com.utils.mappers.EventMapper;

@Repository
public class EventRepository implements IEventRepository {
    final private JpaEventRepository _jpaEventRepository;

    public EventRepository(JpaEventRepository jpaEventRepository) {
        this._jpaEventRepository = jpaEventRepository;
    }

    @Autowired
    private EventMapper _eventMapper;
    
    @Override
    public Event save(Event event) {

        var jpaEvent = new JpaEventEntity(event);
        _jpaEventRepository.save(jpaEvent);
        return _eventMapper.jpaToDomain(jpaEvent);
    }

    @Override
    public Optional<Event> findById(UUID id) {
        Optional<JpaEventEntity> jpaEvent = _jpaEventRepository.findById(id);
        return Optional.of(_eventMapper.jpaToDomain(jpaEvent.get()));
    }

    @Override
    public List<Event> findAll() {
        List<JpaEventEntity> jpaEvents = _jpaEventRepository.findAll();
        return jpaEvents
            .stream()
            .map(_eventMapper::jpaToDomain)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(UUID id) {
        _jpaEventRepository.deleteById(id);
    }

    @Override
    public Page<Event> findUpcomingEvents(Date currentDate, Pageable pageable) {
        return _jpaEventRepository.findUpcomingEvents(currentDate, pageable);
    }

    @Override
    public Page<EventAddressProjection> findFilteredEvents(String city, String uf, Date startDate, Date endDate,
            Pageable pageable) {
        return _jpaEventRepository.findFilteredEvents(city, uf, startDate, endDate, pageable);
    }

    @Override
    public Optional<Event> findByIdForUpdate(UUID id) {
        return _jpaEventRepository.findByIdForUpdate(id);
    }
    
}
