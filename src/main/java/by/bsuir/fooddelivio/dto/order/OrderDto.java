package by.bsuir.fooddelivio.dto.order;

import lombok.Data;

import java.util.List;

@Data
public class OrderDto {
    List<DishInOrderDto> dishes;
}
