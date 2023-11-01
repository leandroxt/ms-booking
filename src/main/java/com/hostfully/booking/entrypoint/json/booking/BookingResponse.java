package com.hostfully.booking.entrypoint.json.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class BookingResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 7019176422637906899L;

    @JsonProperty("id")
    private Long id;
    @JsonProperty("propertyId")
    private Long propertyId;
    @JsonProperty("startDate")
    private LocalDateTime startDate;
    @JsonProperty("finalDate")
    private LocalDateTime finalDate;
    @JsonProperty("guest")
    private GuestResponse guest;
}
