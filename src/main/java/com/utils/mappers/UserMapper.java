package com.utils.mappers;

import java.util.Date;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import com.adapters.outbound.entites.JpaUserEntity;
import com.domain.user.User;
import com.domain.user.dto.UserDetailDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mappings({
        @Mapping(target = "id", ignore = true),
        @Mapping(source = "jpa.name", target = "name"),
        @Mapping(source = "jpa.email", target = "email"),
        @Mapping(source = "jpa.password", target = "password"),
        @Mapping(source = "jpa.role", target = "role"),
        @Mapping(source = "jpa.createAt", target = "createAt"),
    })
    User jpaToDomain(JpaUserEntity jpa);
    
    @Mappings({
        @Mapping(source = "domain.name", target = "name"),
        @Mapping(source = "domain.email", target = "email"),
        @Mapping(source = "domain.role", target = "role"),
    })
    UserDetailDTO domainToDetailDTO(User domain);



    @Named("epochToDate")
    default Date epochToDate(Long timestamp) {
        return timestamp != null ? new Date(timestamp) : null;
    }

    @Named("dateToEpoch")
    default Long dateToEpoch(Date date) {
        return date != null ? date.getTime() : null;
    }
}
