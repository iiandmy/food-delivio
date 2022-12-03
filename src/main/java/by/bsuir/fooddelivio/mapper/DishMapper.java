package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.menu.DishDto;
import by.bsuir.fooddelivio.dto.menu.DishResponseDto;
import by.bsuir.fooddelivio.entity.Dish;

public class DishMapper {
    public static Dish dishDtoToEntity(DishDto dto) {
        return Dish.builder()
                .description(dto.getDescription())
                .calories(dto.getCalories())
                .name(dto.getName())
                .price(dto.getPrice())
                .build();
    }

    public static DishResponseDto dishEntityToDto(Dish dish) {
        return DishResponseDto.builder()
                .id(dish.getId())
                .menuId(dish.getMenu().getId())
                .description(dish.getDescription())
                .name(dish.getName())
                .calories(dish.getCalories())
                .imageUrl(dish.getImageUrl())
                .price(dish.getPrice())
                .build();
    }
}
