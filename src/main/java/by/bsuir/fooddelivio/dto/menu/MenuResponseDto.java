package by.bsuir.fooddelivio.dto.menu;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MenuResponseDto {
    private Long id;
    private Long restaurantId;
    private String name;
    private List<DishResponseDto> dishes;
}
