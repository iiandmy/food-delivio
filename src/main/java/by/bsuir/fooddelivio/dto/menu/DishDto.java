package by.bsuir.fooddelivio.dto.menu;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DishDto {
    private Long menuId;
    private String name;
    private String description;
    private double price;
    private int calories;
}
