package com.hostfully.booking.entrypoint.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(value = PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class DataResponse<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5022269628598651453L;

    @JsonProperty("data")
    private T t;
}
