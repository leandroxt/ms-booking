package com.hostfully.booking.gateway.data.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "property")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PropertyModel {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column
    private String description;
    @OneToMany(mappedBy = "property")
    private Set<BlockModel> blocks;
    @OneToMany(mappedBy = "property")
    private Set<BookingModel> bookings;
}
