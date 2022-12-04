package by.bsuir.fooddelivio.service;

import by.bsuir.fooddelivio.dto.order.OrderDto;
import by.bsuir.fooddelivio.entity.*;
import by.bsuir.fooddelivio.mapper.DishInOrderMapper;
import by.bsuir.fooddelivio.mapper.OrderMapper;
import by.bsuir.fooddelivio.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishRepository dishRepository;
    private final DishInOrderRepository dishInOrderRepository;
    private final ReviewRepository reviewRepository;

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    public Order createOrder(OrderDto orderDto, User user) {
        Order order = OrderMapper.orderDtoToEntity(orderDto);
        String orderDate = getCurrentDateAndTime();
        order.setDishes(
                orderDto.getDishes().stream()
                .map((dto) -> {
                    DishInOrder dish = DishInOrderMapper.dishDtoToEntity(dto);
                    dish.setOrder(order);
                    dish.setDish(dishRepository.findById(dto.getDishId()).get());
                    return dish;
                }).collect(Collectors.toList())
        );
        user.getOrders().add(order);
        order.setOrderDate(orderDate);
        order.setUser(user);
        orderRepository.save(order);
        dishInOrderRepository.saveAll(order.getDishes());
        return order;
    }

    public Review getReview(Long orderId) {
        Optional<Review> review = reviewRepository.findByOrderId(orderId);
        if (review.isEmpty()) {
            return null;
        }
        return review.get();
    }

    public Review addReviewToOrder(User user, Review review, Order order, Long restaurantId) {
        Review reviewFromDb = getReview(order.getId());
        if (reviewFromDb != null) {
            reviewFromDb.setReviewDate(getCurrentDateAndTime());
            reviewFromDb.setReviewMark(review.getReviewMark());
            reviewFromDb.setReviewText(review.getReviewText());
            return reviewFromDb;
        }
        review.setAuthor(user);
        review.setReviewDate(getCurrentDateAndTime());
        review.setRefersTo(restaurantRepository.findById(restaurantId).get());
        review.setOrder(order);
        user.getReviews().add(review);
        reviewRepository.save(review);
        return review;
    }

    private String getCurrentDateAndTime() {
        return new GregorianCalendar().toZonedDateTime()
                .format(DateTimeFormatter.ofPattern("d MMM uuuu, HH:mm"));
    }
}
