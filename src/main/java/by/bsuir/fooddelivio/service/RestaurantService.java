package by.bsuir.fooddelivio.service;

import by.bsuir.fooddelivio.dto.RestaurantDto;
import by.bsuir.fooddelivio.entity.Restaurant;
import by.bsuir.fooddelivio.entity.Review;
import by.bsuir.fooddelivio.mapper.RestaurantMapper;
import by.bsuir.fooddelivio.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {
    private final RestaurantRepository restaurantRepo;

    public void createRestaurant(RestaurantDto dto) {
        Restaurant restaurant = RestaurantMapper.restaurantDtoToEntity(dto);
        restaurantRepo.save(restaurant);
    }

    public double getRatingForRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).get();
        return restaurant.getReviews()
                .stream()
                .mapToDouble(Review::getReviewMark)
                .reduce(0.0, (mark1, mark2) -> (mark1 + mark2) / 2);
    }

    public List<Restaurant> getTop5Restaurants() {
        return new ArrayList<>();
    }
}
