package com.hostfully.booking.entrypoint.json.block;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@Setter(PRIVATE)
@Builder(toBuilder = true)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PRIVATE)
public class BlockResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 8348625921764913958L;

    @JsonProperty("id")
    private Long id;
    @JsonProperty("propertyId")
    private Long propertyId;
    @JsonProperty("startDate")
    private LocalDateTime startDate;
    @JsonProperty("finalDate")
    private LocalDateTime finalDate;
    @JsonProperty("description")
    private String description;
}
