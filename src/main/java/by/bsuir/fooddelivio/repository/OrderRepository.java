package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
