package com.utils.mappers;


import com.adapters.outbound.entites.JpaEventEntity;
import com.domain.address.Address;
import com.domain.coupon.Coupon;
import com.domain.event.Event;
import com.domain.event.EventDetailsDTO;
import com.domain.event.EventRequestDTO;
import com.domain.event.EventResponseDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface EventMapper {

    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "dto.title", target = "title"),
        @Mapping(source = "dto.description", target = "description"),
        @Mapping(target = "imgUrl", source = "imgUrl"),
        @Mapping(source = "dto.eventUrl", target = "eventUrl"),
        @Mapping(source = "dto.date", target = "date", qualifiedByName = "epochToDate"),
        @Mapping(source = "dto.remote", target = "remote"),
    })
    Event toEntity(EventRequestDTO dto, String imgUrl);

    @Mappings({
        @Mapping(source = "jpa.id", target = "id"),
        @Mapping(source = "jpa.title", target = "title"),
        @Mapping(source = "jpa.description", target = "description"),
        @Mapping(source = "jpa.imgUrl", target = "imgUrl"),
        @Mapping(source = "jpa.eventUrl", target = "eventUrl"),
        @Mapping(source = "jpa.remote", target = "remote"),
        @Mapping(source = "jpa.date", target = "date"),
    })
    Event jpaToDomain(JpaEventEntity jpa);    
    
    default EventResponseDTO domainToResponseDTO(Event domain, Optional<Address> address){
        String city = address.map(Address::getCity).orElse("");
        String state = address.map(Address::getUf).orElse("");
        
        return new EventResponseDTO(
            domain.getId(),
            domain.getTitle(),
            domain.getDescription(),
            domain.getDate(),
            city,
            state,
            domain.getRemote(),
            domain.getEventUrl(),
            domain.getImgUrl()
        );
    }
    
    @Mappings({
        @Mapping(source = "jpa.id", target = "id"),
        @Mapping(source = "jpa.title", target = "title"),
        @Mapping(source = "jpa.date", target = "date"),
        @Mapping(source = "jpa.city", target = "city"),
        @Mapping(source = "jpa.uf", target = "uf"),
        @Mapping(source = "jpa.remote", target = "remote"),
        @Mapping(source = "jpa.eventUrl", target = "eventUrl"),
        @Mapping(source = "jpa.imgUrl", target = "imgUrl"),
    })
    default EventDetailsDTO domainToDetailsDTO(Event event, Optional<Address> address, List<Coupon> coupons){
        String city = address.map(Address::getCity).orElse("");
        String state = address.map(Address::getUf).orElse("");
        
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
                city,
                state,
                event.getImgUrl(),
                event.getEventUrl(),
                couponDTOs);
    };


    @Named("epochToDate")
    default Date epochToDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp) : null;
    }

    @Named("dateToEpoch")
    default Long dateToEpoch(Date date) {
        return date != null ? date.getTime() : null;
    }
}
