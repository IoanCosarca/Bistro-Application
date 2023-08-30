package com.ntt.bistroapplication.mapper;

import com.ntt.bistroapplication.model.PlacedOrder;
import com.ntt.bistroapplication.model.PlacedOrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    PlacedOrderDTO orderToOrderDTO(PlacedOrder placedOrder);

    @Mapping(target = "id", ignore = true)
    PlacedOrder orderDTOtoOrder(PlacedOrderDTO placedOrderDTO);
}
