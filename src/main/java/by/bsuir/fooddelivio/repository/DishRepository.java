package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
