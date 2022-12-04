package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Menu;
import by.bsuir.fooddelivio.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Transactional
    @Query(
            value = "SELECT * FROM reviews WHERE order_id = ?1",
            nativeQuery = true
    )
    Optional<Review> findByOrderId(Long orderId);
}
