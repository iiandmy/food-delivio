package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByName(String name);
    Boolean existsByName(String name);
}
