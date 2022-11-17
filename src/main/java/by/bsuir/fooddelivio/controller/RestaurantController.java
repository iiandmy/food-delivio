package by.bsuir.fooddelivio.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class RestaurantController {
    @GetMapping("/restaurants")
    public String getRestaurants() {
        return "Hello";
    }
}
