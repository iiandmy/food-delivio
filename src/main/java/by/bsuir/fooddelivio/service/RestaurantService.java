package by.bsuir.fooddelivio.service;

import by.bsuir.fooddelivio.dto.RestaurantDto;
import by.bsuir.fooddelivio.entity.Menu;
import by.bsuir.fooddelivio.entity.Restaurant;
import by.bsuir.fooddelivio.entity.Review;
import by.bsuir.fooddelivio.mapper.RestaurantMapper;
import by.bsuir.fooddelivio.repository.RestaurantRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class RestaurantService {
    private final RestaurantRepository restaurantRepo;
    private final Cloudinary cloudinary;

    public void createRestaurant(RestaurantDto dto) {
        if (restaurantRepo.existsByName(dto.getName())) {
            return;
        }
        Restaurant restaurant = RestaurantMapper.restaurantDtoToEntity(dto);
        restaurantRepo.save(restaurant);
    }

    public void setRestaurantImage(
            Long restaurantId,
            MultipartFile image
    ) throws IOException {
        String url = uploadPicture(image);
        Restaurant restaurant = restaurantRepo.findById(restaurantId).get();
        restaurant.setImageUrl(url);
    }

    public List<Menu> getRestaurantMenus(Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).get();
        return restaurant.getMenus();
    }

    public Restaurant getRestaurantByName(String name) {
        Optional<Restaurant> restaurant = restaurantRepo.findByName(name);
        if (restaurant.isEmpty()) {
            throw new IllegalArgumentException("Doesn't exists");
        }
        return restaurant.get();
    }

    public List<Restaurant> getRestaurants() {
        return restaurantRepo.findAll();
    }

    private String uploadPicture(MultipartFile pic) throws IOException {
        Map result = cloudinary.uploader().upload(pic.getBytes(), ObjectUtils.emptyMap());
        return String.valueOf(result.get("url"));
    }

    public double getRatingForRestaurant(Long restaurantId) {
        Restaurant restaurant = restaurantRepo.findById(restaurantId).get();
        List<Double> marks = restaurant.getReviews()
                .stream()
                .mapToDouble(Review::getReviewMark).boxed().toList();
        return marks.stream().reduce(0.0, Double::sum) / marks.size();
    }

    public List<Restaurant> getTop5Restaurants() {
        return new ArrayList<>();
    }
}
