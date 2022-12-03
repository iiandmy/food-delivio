package by.bsuir.fooddelivio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestaurantResponseDto {
    private Long id;
    private String name;
    private String address;
    private String workTime;
    private String imageUrl;
}
