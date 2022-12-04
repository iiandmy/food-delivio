package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.dto.RestaurantDto;
import by.bsuir.fooddelivio.dto.RestaurantResponseDto;
import by.bsuir.fooddelivio.dto.menu.MenuResponseDto;
import by.bsuir.fooddelivio.entity.Restaurant;
import by.bsuir.fooddelivio.mapper.MenuMapper;
import by.bsuir.fooddelivio.mapper.RestaurantMapper;
import by.bsuir.fooddelivio.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/get_restaurants")
    public ResponseEntity<List<RestaurantResponseDto>> getRestaurants() {
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        List<RestaurantResponseDto> response = restaurants
                .stream().map(RestaurantMapper::restaurantToResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get_restaurant_menus/{restaurantId}")
    public ResponseEntity<List<MenuResponseDto>> getRestaurantMenus(@PathVariable("restaurantId") Long restaurantId) {
        return ResponseEntity.ok(
                restaurantService.getRestaurantMenus(restaurantId).stream()
                        .map(MenuMapper::menuEntityToDto).collect(Collectors.toList())
        );
    }

    @PostMapping("/admin/add_restaurant")
    public ResponseEntity<RestaurantResponseDto> createRestaurant(@RequestBody RestaurantDto dto) {
        restaurantService.createRestaurant(dto);
        return ResponseEntity.ok(
                RestaurantMapper.restaurantToResponseDto(restaurantService.getRestaurantByName(dto.getName()))
        );
    }

    @PostMapping("/admin/set_restaurant_image/{restaurantId}")
    public ResponseEntity<String> uploadRestaurantImage(
            @PathVariable("restaurantId") Long restaurantId,
            @ModelAttribute("image") MultipartFile image
    ) throws IOException {
        restaurantService.setRestaurantImage(restaurantId, image);
        return ResponseEntity.ok("Image set");
    }

    @GetMapping("/get_rating/{restaurantId}")
    public ResponseEntity<Double> getRating(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getRatingForRestaurant(restaurantId));
    }
}
