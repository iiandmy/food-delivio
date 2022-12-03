package by.bsuir.fooddelivio.dto.menu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuDto {
    private Long restaurantId;
    private String name;
}
