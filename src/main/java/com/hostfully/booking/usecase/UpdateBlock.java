package com.hostfully.booking.usecase;

import com.hostfully.booking.domain.Block;
import com.hostfully.booking.gateway.data.BlockRepository;
import com.hostfully.booking.gateway.data.model.BlockModel;
import com.hostfully.booking.usecase.mapper.BlockMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;

import static com.hostfully.booking.domain.CheckAvailabilityType.CHECK_BOOKING_ONLY;
import static java.util.Optional.ofNullable;

@Slf4j
@Component
public class UpdateBlock {

    final private BlockRepository blockRepo;
    private final BlockDataValidation blockValidation;
    private final IsDateRangeAvailable isDateRangeAvailable;

    public UpdateBlock(final BlockRepository blockRepo,
                       final BlockDataValidation blockValidation,
                       final IsDateRangeAvailable isDateRangeAvailable) {
        this.blockRepo = blockRepo;
        this.blockValidation = blockValidation;
        this.isDateRangeAvailable = isDateRangeAvailable;
    }

    public Block execute(final Block block) {
        log.info("executing block update");
        if (ofNullable(block.getId()).isEmpty()) {
            throw new InvalidParameterException("cannot update a block without providing the block id");
        }

        blockValidation.execute(block);
        final boolean isAvailable = isDateRangeAvailable.execute(CHECK_BOOKING_ONLY, block.getPropertyId(), block.getStartDate(), block.getFinalDate());
        if (!isAvailable) {
            throw new InvalidParameterException("cannot update block: booking already exists for date range");
        }

        final BlockModel model = BlockMapper.INSTANCE.mapToModel(block);
        final BlockModel updatedModel = blockRepo.save(model);

        log.info("block successfully updated");
        return BlockMapper.INSTANCE.mapToBlock(updatedModel);
    }
}
