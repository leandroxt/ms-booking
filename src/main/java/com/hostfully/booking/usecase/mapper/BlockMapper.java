package com.hostfully.booking.usecase.mapper;

import com.hostfully.booking.domain.Block;
import com.hostfully.booking.gateway.data.model.BlockModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public abstract class BlockMapper {

     public static BlockMapper INSTANCE = getMapper(BlockMapper.class);

     public abstract BlockModel mapToModel(final Block block);

     @Mapping(source = "property.id", target = "propertyId")
     public abstract Block mapToBlock(final BlockModel model);
}
