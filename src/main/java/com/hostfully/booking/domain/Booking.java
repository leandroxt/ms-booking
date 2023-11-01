package com.hostfully.booking.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static java.util.Optional.ofNullable;
import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class Booking implements Serializable {
    @Serial
    private static final long serialVersionUID = 8191160057146597662L;

    private Long id;
    private Long propertyId;
    private LocalDateTime startDate;
    private LocalDateTime finalDate;
    private Guest guest;

    public boolean isEmptyDates() {
        return ofNullable(startDate).isEmpty() || ofNullable(finalDate).isEmpty();
    }

    public boolean isValidDates() {
        return startDate.isAfter(now()) && startDate.isBefore(finalDate);
    }
}
