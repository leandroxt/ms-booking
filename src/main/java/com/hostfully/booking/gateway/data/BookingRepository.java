package com.hostfully.booking.gateway.data;

import com.hostfully.booking.gateway.data.model.BookingModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface BookingRepository extends JpaRepository<BookingModel, Long> {

    @Query("SELECT count(1) FROM BookingModel b WHERE b.property.id = :propertyId AND b.startDate BETWEEN :startDate and :finalDate OR b.finalDate BETWEEN :startDate and :finalDate")
    Integer countByBetweenStartAndFinalDate(@Param("propertyId") final Long id,
                                            @Param("startDate") final LocalDateTime startDate,
                                            @Param("finalDate") final LocalDateTime finalDate
    );
}
