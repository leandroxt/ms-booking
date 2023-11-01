package com.hostfully.booking.entrypoint.mapper;

import com.hostfully.booking.domain.Block;
import com.hostfully.booking.entrypoint.json.block.BlockResponse;
import org.mapstruct.Mapper;

import java.time.LocalDateTime;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class BlockResponseMapper {
    public static BlockResponseMapper INSTANCE = getMapper(BlockResponseMapper.class);

    public abstract Block mapToBlock(LocalDateTime startDate, LocalDateTime finalDate);

    public abstract Block mapToBlock(BlockResponse input);

    public abstract BlockResponse mapToBlockResponse(Block block);
}
