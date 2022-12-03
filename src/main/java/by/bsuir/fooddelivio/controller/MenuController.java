package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.dto.menu.DishDto;
import by.bsuir.fooddelivio.dto.menu.DishResponseDto;
import by.bsuir.fooddelivio.dto.menu.MenuDto;
import by.bsuir.fooddelivio.dto.menu.MenuResponseDto;
import by.bsuir.fooddelivio.entity.Dish;
import by.bsuir.fooddelivio.entity.Menu;
import by.bsuir.fooddelivio.mapper.DishMapper;
import by.bsuir.fooddelivio.mapper.MenuMapper;
import by.bsuir.fooddelivio.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class MenuController {
    private final MenuService menuService;

    @PostMapping("/admin/add_menu")
    public ResponseEntity<MenuResponseDto> addMenu(@RequestBody MenuDto dto) {
        Menu menu = menuService.saveMenu(dto);
        return ResponseEntity.ok(MenuMapper.menuEntityToDto(menu));
    }

    @PostMapping("/admin/add_dish")
    public ResponseEntity<DishResponseDto> addDish(@RequestBody DishDto dto) {
        Dish dish = menuService.saveDish(dto);
        return ResponseEntity.ok(DishMapper.dishEntityToDto(dish));
    }

    @PostMapping("/admin/set_dish_image/{id}")
    public ResponseEntity<String> uploadRestaurantImage(
            @PathVariable("id") Long dishId,
            @ModelAttribute("image") MultipartFile image
    ) throws IOException {
        menuService.setDishImage(dishId, image);
        return ResponseEntity.ok("Image set");
    }

    @GetMapping("/get_menu/{id}")
    public ResponseEntity<MenuResponseDto> getMenu(@PathVariable("id") Long menuId) {
        Menu menu = menuService.getMenu(menuId);
        return ResponseEntity.ok(MenuMapper.menuEntityToDto(menu));
    }

    @GetMapping("/get_dish/{id}")
    public ResponseEntity<DishResponseDto> getDish(@PathVariable("id") Long dishId) {
        Dish dish = menuService.getDish(dishId);
        return ResponseEntity.ok(DishMapper.dishEntityToDto(dish));
    }
}
