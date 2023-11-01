package com.hostfully.booking.usecase;

import com.hostfully.booking.gateway.data.BlockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteBlock {

    private final BlockRepository blockRepo;

    public DeleteBlock(final BlockRepository blockRepo) {
        this.blockRepo = blockRepo;
    }

    public void execute(final Long id) {
        log.info("executing block deletion for block ID: {}", id);
        blockRepo.deleteById(id);
        log.info("successfully deleted block");
    }
}
