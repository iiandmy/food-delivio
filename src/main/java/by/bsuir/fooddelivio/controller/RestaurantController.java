package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.dto.RestaurantDto;
import by.bsuir.fooddelivio.entity.Restaurant;
import by.bsuir.fooddelivio.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestaurantController {
    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public ResponseEntity<String> getRestaurants() {
        return ResponseEntity.ok("123");
    }

    @PostMapping("/add_restaurant")
    public ResponseEntity<String> createRestaurant(@RequestBody RestaurantDto dto) {
        restaurantService.createRestaurant(dto);
        return ResponseEntity.ok("restaurant created successfully");
    }

    @GetMapping("/get_rating/{restaurantId}")
    public ResponseEntity<Double> getRating(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(restaurantService.getRatingForRestaurant(restaurantId));
    }
}
