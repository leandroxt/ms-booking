package com.hostfully.booking.entrypoint;

import com.hostfully.booking.domain.Booking;
import com.hostfully.booking.entrypoint.json.DataResponse;
import com.hostfully.booking.entrypoint.json.booking.BookingResponse;
import com.hostfully.booking.entrypoint.mapper.BookingResponseMapper;
import com.hostfully.booking.usecase.CreateBooking;
import com.hostfully.booking.usecase.DeleteBooking;
import com.hostfully.booking.usecase.UpdateBooking;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/booking")
public class BookingController {

    private final CreateBooking createBooking;
    private final DeleteBooking deleteBooking;
    private final UpdateBooking updateBooking;

    @Autowired
    public BookingController(final CreateBooking createBooking,
                             final DeleteBooking deleteBooking,
                             final UpdateBooking updateBooking) {
        this.createBooking = createBooking;
        this.deleteBooking = deleteBooking;
        this.updateBooking = updateBooking;
    }

    @PostMapping
    public ResponseEntity<DataResponse<BookingResponse>> createBooking(@RequestBody final BookingResponse input) {
        log.info("creating booking for property: {}, start date: {}, and final date {}",
                input.getPropertyId(),
                input.getStartDate(),
                input.getFinalDate());

        final Booking booking = BookingResponseMapper.INSTANCE.mapToBooking(input);
        final Booking createdBooking = createBooking.execute(booking);
        final BookingResponse response = BookingResponseMapper.INSTANCE.mapToResponse(createdBooking);

        log.info("booking successfully created");
        return ResponseEntity.ok(DataResponse.<BookingResponse>builder().t(response).build());
    }

    @PutMapping
    public ResponseEntity<DataResponse<BookingResponse>> updateBooking(@RequestBody final BookingResponse input) {
        log.info("updating booking for property: {}, start date: {}, and final date {}",
                input.getPropertyId(),
                input.getStartDate(),
                input.getFinalDate());
        final Booking booking = BookingResponseMapper.INSTANCE.mapToBooking(input);
        final Booking updatedBooking = updateBooking.execute(booking);
        final BookingResponse response = BookingResponseMapper.INSTANCE.mapToResponse(updatedBooking);

        log.info("booking successfully updated");
        return ResponseEntity.ok(DataResponse.<BookingResponse>builder().t(response).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBooking(@PathVariable("id") final Long id) {
        log.info("deleting booking with ID {}", id);
        deleteBooking.execute(id);
        log.info("booking deletion successfully executed");
        return ResponseEntity.ok().build();
    }

}
