package com.adapters.outbound.entites;

import java.util.Date;
import java.util.UUID;

import com.domain.Enum.RegistrationStatus;
import com.domain.registration.Registration;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "registration")
@Entity(name = "Registration")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaRegistrationEntity {
    @Id
    @GeneratedValue
    private UUID id;
    private Date registeredAt;
    @Column(nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelledAt;
    private Date createdAt;
    private Date updatedAt;
    @ManyToOne
    private JpaUserEntity user;
    @ManyToOne
    private JpaEventEntity event;
    @Enumerated(EnumType.STRING)
    private RegistrationStatus status;

    public JpaRegistrationEntity(Registration registration) {
        this.id = registration.getId();
        this.user = new JpaUserEntity(registration.getUser());
        this.event = new JpaEventEntity(registration.getEvent());
        this.registeredAt = registration.getRegisteredAt();
        this.createdAt = registration.getCreatedAt();
        this.updatedAt = registration.getUpdatedAt();
        this.status = registration.getStatus();
        this.cancelledAt = registration.getCancelledAt().orElse(null);
    }

    public void cancel(){
        this.status = RegistrationStatus.CANCELLED;
        this.cancelledAt = new Date();
        this.updatedAt = new Date();
    }
}
