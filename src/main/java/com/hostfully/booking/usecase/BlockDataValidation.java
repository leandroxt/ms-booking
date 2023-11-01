package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.Block;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

@Slf4j
@Component
public class BlockDataValidation {

    public void execute(final Block block) {
        log.info("executing dates validation");
        if (block.isEmptyDates()) {
            throw new InvalidParameterException("date range must be informed");
        }
        if (!block.isValidDates()) {
            throw new InvalidParameterException("start date cannot be in the past and start date must be before final date");
        }
    }
}
