package com.hostfully.booking.entrypoint;

import com.hostfully.booking.domain.Block;
import com.hostfully.booking.entrypoint.json.DataResponse;
import com.hostfully.booking.entrypoint.json.block.BlockResponse;
import com.hostfully.booking.entrypoint.mapper.BlockResponseMapper;
import com.hostfully.booking.usecase.CreateBlock;
import com.hostfully.booking.usecase.DeleteBlock;
import com.hostfully.booking.usecase.UpdateBlock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/block")
public class BlockController {

    private final CreateBlock createBlock;
    private final DeleteBlock deleteBlock;
    private final UpdateBlock updateBlock;

    @Autowired
    public BlockController(final CreateBlock createBlock,
                           final DeleteBlock deleteBlock,
                           final UpdateBlock updateBlock) {
        this.createBlock = createBlock;
        this.deleteBlock = deleteBlock;
        this.updateBlock = updateBlock;
    }

    @PostMapping
    public ResponseEntity<DataResponse<BlockResponse>> createBlock(@RequestBody final BlockResponse input) {
        log.info("creating block with start date [{}] and final date [{}]",
                input.getStartDate(),
                input.getFinalDate());
        final Block block = BlockResponseMapper.INSTANCE.mapToBlock(input);
        final Block blockCreated = createBlock.execute(block);
        final BlockResponse response = BlockResponseMapper.INSTANCE.mapToBlockResponse(blockCreated);
        log.info("successfully created rent blocking in range dates: [{}] - [{}]",
                response.getStartDate(), response.getFinalDate());
        return ResponseEntity.ok(DataResponse.<BlockResponse>builder().t(response).build());
    }

    @PutMapping
    public ResponseEntity<DataResponse<BlockResponse>> updateBlock(@RequestBody final BlockResponse input) {
        log.info("updating block with ID: {}", input.getId());
        final Block block = BlockResponseMapper.INSTANCE.mapToBlock(input);
        final Block updated = updateBlock.execute(block);
        final BlockResponse response = BlockResponseMapper.INSTANCE.mapToBlockResponse(updated);
        return ResponseEntity.ok(DataResponse.<BlockResponse>builder().t(response).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlock(@PathVariable("id") final Long id) {
        log.info("deleting block with ID: {}", id);
        deleteBlock.execute(id);
        log.info("block deletion successfully executed");
        return ResponseEntity.ok().build();
    }

}
