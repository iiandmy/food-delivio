package by.bsuir.fooddelivio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantDto {
    String name;
    String address;
    String workTime;
    String imageUrl;
}
