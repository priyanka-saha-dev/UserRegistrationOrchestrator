package com.api.user.registration.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "NOTIFICATIONS")
public class Notifications {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_uuid", referencedColumnName = "uuid")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "initiator_uuid", referencedColumnName = "uuid")
    private Users initiator;

    @OneToOne
    @JoinColumn(name = "user_contact_id", referencedColumnName = "id")
    private Contact userContact;

    private Boolean status;

    private String message;
}
