package by.bsuir.fooddelivio.repository;

import by.bsuir.fooddelivio.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
