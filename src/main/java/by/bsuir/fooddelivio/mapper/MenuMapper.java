package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.menu.MenuDto;
import by.bsuir.fooddelivio.dto.menu.MenuResponseDto;
import by.bsuir.fooddelivio.entity.Menu;

import java.util.stream.Collectors;

public class MenuMapper {
    public static Menu menuDtoToEntity(MenuDto dto) {
        return Menu.builder()
                .name(dto.getName())
                .build();
    }

    public static MenuResponseDto menuEntityToDto(Menu menu) {
        return MenuResponseDto.builder()
                .id(menu.getId())
                .name(menu.getName())
                .restaurantId(menu.getRestaurant().getId())
                .dishes(menu.getDishes()
                        .stream().map(DishMapper::dishEntityToDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
