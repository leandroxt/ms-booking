package com.hostfully.booking.gateway.data;

import com.hostfully.booking.gateway.data.model.PropertyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends JpaRepository<PropertyModel, Long> {
}
