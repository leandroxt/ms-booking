package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.Booking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Slf4j
@Component
public class BookingValidation {

    public void execute(final Booking booking) {
        log.info("executing range dates validation");
        if (booking.isEmptyDates()) {
            throw new InvalidParameterException("date range must be informed");
        }
        if (!booking.isValidDates()) {
            throw new InvalidParameterException("start date cannot be in the past and start date must be before final date");
        }
    }
}
