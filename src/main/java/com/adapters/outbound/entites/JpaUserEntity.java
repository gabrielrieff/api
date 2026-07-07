package com.adapters.outbound.entites;

import java.util.*;

import com.domain.Enum.UserRole;
import com.domain.user.User;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "users")
@Entity(name = "users")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JpaUserEntity{
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private String email;
    private String password;
    private UserRole role;
    @Column(name = "createat", nullable = false, insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    public JpaUserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.createAt = user.getCreateAt();
    }
}
