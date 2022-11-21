package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.RestaurantDto;
import by.bsuir.fooddelivio.entity.Restaurant;

public class RestaurantMapper {
    public static Restaurant restaurantDtoToEntity(RestaurantDto dto) {
        return Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .workTime(dto.getWorkTime())
                .build();
    }
}
