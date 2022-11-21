package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
