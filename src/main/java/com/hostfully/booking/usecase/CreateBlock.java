package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.Block;
import com.hostfully.booking.gateway.data.BlockRepository;
import com.hostfully.booking.gateway.data.PropertyRepository;
import com.hostfully.booking.gateway.data.model.BlockModel;
import com.hostfully.booking.gateway.data.model.PropertyModel;
import com.hostfully.booking.usecase.mapper.BlockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

import static com.hostfully.booking.domain.CheckAvailabilityType.CHECK_BOOKING_ONLY;


@Slf4j
@Component
public class CreateBlock {

    private final PropertyRepository propertyRepo;
    private final BlockRepository blockRepo;
    private final BlockDataValidation blockValidation;
    private final IsDateRangeAvailable isDateRangeAvailable;

    public CreateBlock(final PropertyRepository propertyRepo,
                       final BlockRepository blockRepo,
                       final BlockDataValidation blockValidation,
                       final IsDateRangeAvailable checkAvailability) {
        this.propertyRepo = propertyRepo;
        this.blockRepo = blockRepo;
        this.blockValidation = blockValidation;
        this.isDateRangeAvailable = checkAvailability;
    }

    public Block execute(final Block block) {
        log.info("executing rent blocking creation");
        final PropertyModel property = propertyRepo.findById(block.getPropertyId())
                .orElseThrow();

        blockValidation.execute(block);
        final boolean isAvailable = isDateRangeAvailable.execute(CHECK_BOOKING_ONLY, property.getId(), block.getStartDate(), block.getFinalDate());
        if (!isAvailable) {
            throw new InvalidParameterException("cannot create block: booking already exists for date range");
        }

        final BlockModel model = BlockMapper.INSTANCE.mapToModel(block);
        model.setProperty(property);
        final BlockModel createdBlock = blockRepo.save(model);

        log.info("block successfully created");
        return BlockMapper.INSTANCE.mapToBlock(createdBlock);
    }
}
