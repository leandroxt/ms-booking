package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.Booking;
import com.hostfully.booking.gateway.data.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteBooking {

    private final BookingRepository bookingRepo;

    public DeleteBooking(final BookingRepository bookingRepo) {
        this.bookingRepo = bookingRepo;
    }

    public void execute(final Long id) {
        log.info("executing booking deletion");
        bookingRepo.deleteById(id);
        log.info("booking deleted successfully");
    }
}
