package com.adapters.outbound.entites;

import java.util.Date;
import java.util.UUID;

import com.domain.event.Event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "event")
@Entity(name = "Event")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaEventEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private String imgUrl;
    private String eventUrl;
    private Boolean remote;
    private Date date;
    private Integer maxParticipants;
    private Integer confirmedParticipants;

    @Version
    private Long version;

    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private JpaAddressEntity address;

    public JpaEventEntity(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.description = event.getDescription();
        this.imgUrl = event.getImgUrl();
        this.eventUrl = event.getEventUrl();
        this.remote = event.getRemote();
        this.date = event.getDate();
        this.maxParticipants = event.getMaxParticipants();
        this.confirmedParticipants = event.getConfirmedParticipants();
        this.version = event.getVersion();
    }
}
