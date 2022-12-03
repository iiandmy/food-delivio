package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.RestaurantDto;
import by.bsuir.fooddelivio.dto.RestaurantResponseDto;
import by.bsuir.fooddelivio.entity.Restaurant;

public class RestaurantMapper {
    public static Restaurant restaurantDtoToEntity(RestaurantDto dto) {
        return Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .workTime(dto.getWorkTime())
                .imageUrl(dto.getImageUrl())
                .build();
    }

    public static RestaurantResponseDto restaurantToResponseDto(Restaurant restaurant) {
        return RestaurantResponseDto.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .workTime(restaurant.getWorkTime())
                .imageUrl(restaurant.getImageUrl())
                .build();
    }
}
