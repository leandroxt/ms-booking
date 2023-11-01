package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.CheckAvailabilityType;
import com.hostfully.booking.gateway.data.BlockRepository;
import com.hostfully.booking.gateway.data.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static java.lang.Boolean.TRUE;

@Slf4j
@Component
public class IsDateRangeAvailable {

    private final BlockRepository blockRepo;
    private final BookingRepository bookingRepo;

    public IsDateRangeAvailable(final BlockRepository blockRepo,
                                final BookingRepository bookingRepo) {
        this.blockRepo = blockRepo;
        this.bookingRepo = bookingRepo;
    }

    public boolean execute(final CheckAvailabilityType check, final Long propertyId, final LocalDateTime startDate, final LocalDateTime finalDate) {
        log.info("checking if date range is available for booking");

        final Integer bookingsCount = bookingRepo.countByBetweenStartAndFinalDate(propertyId, startDate, finalDate);
        final Boolean isBookingsAvailable = bookingsCount.compareTo(0) == 0;
        final Boolean isBlocksAvailable = isBlocksAvailable(check, propertyId, startDate, finalDate);

        final boolean isAvailable = isBookingsAvailable && isBlocksAvailable;

        log.info("date range is {}", isAvailable);
        return isAvailable;
    }

    private Boolean isBlocksAvailable(final CheckAvailabilityType check, final Long propertyId, final LocalDateTime startDate, final LocalDateTime finalDate) {
        if (CheckAvailabilityType.CHECK_ALL.equals(check)) {
            final Integer blocksCount = blockRepo.countByBetweenStartAndFinalDate(propertyId, startDate, finalDate);
            return blocksCount.compareTo(0) == 0;
        }
        return TRUE;
    }
}
