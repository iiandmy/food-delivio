package by.bsuir.fooddelivio.dto.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrderResponseDto {
    private Long orderId;
    private Long userId;
    private List<DishInOrderResponseDto> dishes;
    private ReviewResponseDto review;
}
