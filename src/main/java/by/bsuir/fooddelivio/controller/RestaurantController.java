package by.bsuir.fooddelivio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestaurantController {
    @GetMapping("/restaurants")
    public ResponseEntity<String> getRestaurants() {
        return ResponseEntity.ok("123");
    }
}
