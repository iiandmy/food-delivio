package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    @Transactional
    @Query(
            value = "SELECT * FROM menus WHERE name = ?1 and restaurant_id = ?2",
            nativeQuery = true
    )
    Optional<Menu> findByNameAndRestaurantId(String name, Long restaurantId);
}
