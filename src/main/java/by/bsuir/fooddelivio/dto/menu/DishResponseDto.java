package by.bsuir.fooddelivio.dto.menu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishResponseDto {
    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private double price;
    private int calories;
    private String imageUrl;
}
