package com.hostfully.booking.entrypoint.mapper;

import com.hostfully.booking.domain.Booking;
import com.hostfully.booking.entrypoint.json.booking.BookingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class BookingResponseMapper {
    public static BookingResponseMapper INSTANCE = getMapper(BookingResponseMapper.class);

    @Mapping(source = "guest.name", target = "guest.name")
    @Mapping(source = "guest.email", target = "guest.email")
    @Mapping(source = "guest.phone", target = "guest.phone")
    public abstract Booking mapToBooking(final BookingResponse response);

    @Mapping(source = "guest.name", target = "guest.name")
    @Mapping(source = "guest.email", target = "guest.email")
    @Mapping(source = "guest.phone", target = "guest.phone")
    public abstract BookingResponse mapToResponse(final Booking booking);
}
