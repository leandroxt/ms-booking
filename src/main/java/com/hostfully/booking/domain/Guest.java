package com.hostfully.booking.domain;

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
public class Guest implements Serializable {
    @Serial
    private static final long serialVersionUID = -3258886076338138663L;

    private String name;
    private String email;
    private String phone;
}
