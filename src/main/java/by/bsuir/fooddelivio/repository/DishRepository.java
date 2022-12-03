package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Dish;
import by.bsuir.fooddelivio.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface DishRepository extends JpaRepository<Dish, Long> {
    @Transactional
    @Query(
            value = "SELECT * FROM dishes WHERE name = ?1 and menu_id = ?2",
            nativeQuery = true
    )
    Optional<Dish> findByNameAndMenuId(String name, Long menuId);
}
