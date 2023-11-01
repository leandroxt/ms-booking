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

@Slf4j
@Component
public class CreateBooking {

    final BookingRepository bookingRepo;
    private final PropertyRepository propertyRepo;
    private final BookingValidation bookingValidation;
    private final IsDateRangeAvailable isDateRangeAvailable;

    public CreateBooking(final PropertyRepository propertyRepo,
                         final BookingRepository bookingRepo,
                         final BookingValidation bookingValidation,
                         final IsDateRangeAvailable isDateRangeAvailable) {
        this.propertyRepo = propertyRepo;
        this.bookingRepo = bookingRepo;
        this.bookingValidation = bookingValidation;
        this.isDateRangeAvailable = isDateRangeAvailable;
    }

    public Booking execute(final Booking booking) {
        log.info("executing booking creation");
        final PropertyModel property = propertyRepo.findById(booking.getPropertyId())
                .orElseThrow();

        bookingValidation.execute(booking);
        final boolean isAvailable = isDateRangeAvailable.execute(CHECK_ALL, property.getId(), booking.getStartDate(), booking.getFinalDate());
        if (!isAvailable) {
            throw new InvalidParameterException("cannot create booking: date range not available");
        }

        final BookingModel model = BookingMapper.INSTANCE.mapToModel(booking);
        model.setProperty(property);
        final BookingModel createdBooking = bookingRepo.save(model);

        log.info("booking successfully created");
        return BookingMapper.INSTANCE.mapToDomain(createdBooking);
    }
}
