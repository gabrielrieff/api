package com.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.event.Event;

public interface IEventRepository extends JpaRepository<Event, UUID>{

}
