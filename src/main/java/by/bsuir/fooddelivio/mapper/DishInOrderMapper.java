package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.order.DishInOrderDto;
import by.bsuir.fooddelivio.dto.order.DishInOrderResponseDto;
import by.bsuir.fooddelivio.entity.DishInOrder;

public class DishInOrderMapper {
    public static DishInOrder dishDtoToEntity(DishInOrderDto dto) {
        return DishInOrder.builder()
                .quantity(dto.getQuantity())
                .build();
    }

    public static DishInOrderResponseDto dishEntityToDto(DishInOrder dto) {
        return DishInOrderResponseDto.builder()
                .id(dto.getId())
                .orderId(dto.getOrder().getId())
                .dishId(dto.getDish().getId())
                .quantity(dto.getQuantity())
                .build();
    }
}
