package com.hostfully.booking.gateway.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "booking")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingModel {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime startDate;
    @Column(nullable = false)
    private LocalDateTime finalDate;
    @Column(name = "guest_name")
    private String guestName;
    @Column(name = "guest_email")
    private String guestEmail;
    @Column(name = "guest_phone")
    private String guestPhone;
    @ManyToOne
    @JoinColumn(name = "property_id", nullable = false)
    private PropertyModel property;
}
