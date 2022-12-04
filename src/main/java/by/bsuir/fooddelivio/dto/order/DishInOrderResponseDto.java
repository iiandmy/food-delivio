package by.bsuir.fooddelivio.dto.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishInOrderResponseDto {
    private Long id;
    private Long orderId;
    private Long dishId;
    private int quantity;
}
