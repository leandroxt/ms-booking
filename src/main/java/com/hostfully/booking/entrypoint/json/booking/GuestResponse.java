package com.hostfully.booking.entrypoint.json.booking;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class GuestResponse implements Serializable {
    @Serial
    private static final long serialVersionUID = 3728012460860755021L;

    @JsonProperty("name")
    private String name;
    @JsonProperty("email")
    private String email;
    @JsonProperty("phone")
    private String phone;
}
