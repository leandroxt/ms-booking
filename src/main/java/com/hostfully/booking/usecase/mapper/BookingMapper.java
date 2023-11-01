package com.hostfully.booking.usecase.mapper;

import com.hostfully.booking.domain.Booking;
import com.hostfully.booking.gateway.data.model.BookingModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class BookingMapper {

    public static BookingMapper INSTANCE = getMapper(BookingMapper.class);

    @Mapping(source = "propertyId", target = "property.id")
    @Mapping(source = "guest.name", target = "guestName")
    @Mapping(source = "guest.email", target = "guestEmail")
    @Mapping(source = "guest.phone", target = "guestPhone")
    public abstract BookingModel mapToModel(final Booking booking);

    @Mapping(source = "property.id", target = "propertyId")
    @Mapping(source = "guestName", target = "guest.name")
    @Mapping(source = "guestEmail", target = "guest.email")
    @Mapping(source = "guestPhone", target = "guest.phone")
    public abstract Booking mapToDomain(final BookingModel model);
}
