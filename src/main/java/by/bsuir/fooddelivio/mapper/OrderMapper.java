package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.order.OrderDto;
import by.bsuir.fooddelivio.dto.order.OrderResponseDto;
import by.bsuir.fooddelivio.entity.Order;
import by.bsuir.fooddelivio.entity.Review;

import java.util.stream.Collectors;

public class OrderMapper {
    public static Order orderDtoToEntity(OrderDto dto) {
        return Order.builder().build();
    }

    public static OrderResponseDto orderEntityToDto(Order order, Review review) {
        return OrderResponseDto.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .dishes(order.getDishes()
                        .stream().map(DishInOrderMapper::dishEntityToDto)
                        .collect(Collectors.toList())
                )
                .review(review == null ? null : ReviewMapper.reviewEntityToDto(review))
                .build();
    }
}
