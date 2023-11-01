package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.Booking;
import com.hostfully.booking.gateway.data.BookingRepository;
import com.hostfully.booking.gateway.data.PropertyRepository;
import com.hostfully.booking.gateway.data.model.BookingModel;
import com.hostfully.booking.gateway.data.model.PropertyModel;
import com.hostfully.booking.usecase.mapper.BookingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

import static com.hostfully.booking.domain.CheckAvailabilityType.CHECK_ALL;
import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class UpdateBooking {

    private final BookingRepository bookingRepo;
    private final PropertyRepository propertyRepo;
    private final BookingValidation bookingValidation;
    private final IsDateRangeAvailable isDateRangeAvailable;

    public UpdateBooking(final BookingRepository bookingRepo,
                         final PropertyRepository propertyRepo,
                         final BookingValidation bookingValidation,
                         final IsDateRangeAvailable isDateRangeAvailable) {
        this.bookingRepo = bookingRepo;
        this.propertyRepo = propertyRepo;
        this.bookingValidation = bookingValidation;
        this.isDateRangeAvailable = isDateRangeAvailable;
    }

    public Booking execute(final Booking booking) {
        log.info("executing booking update");
        if (ofNullable(booking.getId()).isEmpty()) {
            throw new InvalidParameterException("cannot update a booking without providing the id");
        }

        final PropertyModel property = propertyRepo.findById(booking.getPropertyId()).orElseThrow();

        bookingValidation.execute(booking);
        final boolean isAvailable = isDateRangeAvailable.execute(CHECK_ALL, property.getId(), booking.getStartDate(), booking.getFinalDate());
        if (!isAvailable) {
            throw new InvalidParameterException("cannot create booking: date range not available");
        }

        final BookingModel model = BookingMapper.INSTANCE.mapToModel(booking);
        model.setProperty(property);
        final BookingModel updatedBooking = bookingRepo.save(model);

        return BookingMapper.INSTANCE.mapToDomain(updatedBooking);
    }
}
