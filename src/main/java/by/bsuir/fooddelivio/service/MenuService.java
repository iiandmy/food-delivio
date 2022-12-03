package by.bsuir.fooddelivio.service;

import by.bsuir.fooddelivio.dto.menu.DishDto;
import by.bsuir.fooddelivio.dto.menu.MenuDto;
import by.bsuir.fooddelivio.entity.Dish;
import by.bsuir.fooddelivio.entity.Menu;
import by.bsuir.fooddelivio.entity.Restaurant;
import by.bsuir.fooddelivio.mapper.DishMapper;
import by.bsuir.fooddelivio.mapper.MenuMapper;
import by.bsuir.fooddelivio.repository.DishRepository;
import by.bsuir.fooddelivio.repository.MenuRepository;
import by.bsuir.fooddelivio.repository.RestaurantRepository;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MenuService {
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final RestaurantRepository restaurantRepository;
    private final Cloudinary cloudinary;

    public Menu saveMenu(MenuDto dto) {
        Menu menu = MenuMapper.menuDtoToEntity(dto);
        Optional<Menu> existingMenu = menuRepository.findByNameAndRestaurantId(
                dto.getName(),
                dto.getRestaurantId()
        );
        if (existingMenu.isPresent()) {
            return existingMenu.get();
        }
        Optional<Restaurant> restaurant = restaurantRepository.findById(dto.getRestaurantId());
        if (restaurant.isEmpty()) {
            throw new IllegalArgumentException();
        }
        menu.setRestaurant(restaurant.get());
        List<Menu> menus = restaurant.get().getMenus();
        menus.add(menu);
        menuRepository.save(menu);
        return menu;
    }

    public Dish saveDish(DishDto dto) {
        Dish dish = DishMapper.dishDtoToEntity(dto);
        Optional<Dish> existingDish = dishRepository.findByNameAndMenuId(
                dto.getName(),
                dto.getMenuId()
        );
        if (existingDish.isPresent()) {
            return existingDish.get();
        }
        Optional<Menu> menu = menuRepository.findById(dto.getMenuId());
        if (menu.isEmpty()) {
            throw new IllegalArgumentException();
        }
        dish.setMenu(menu.get());
        List<Dish> dishes = menu.get().getDishes();
        dishes.add(dish);
        dishRepository.save(dish);
        return dish;
    }

    public Menu getMenu(Long id) {
        Optional<Menu> menu = menuRepository.findById(id);
        if (menu.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return menu.get();
    }

    public Dish getDish(Long id) {
        Optional<Dish> dish = dishRepository.findById(id);
        if (dish.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return dish.get();
    }

    public void setDishImage(
            Long dishId,
            MultipartFile image
    ) throws IOException {
        String url = uploadPicture(image);
        Dish dish = dishRepository.findById(dishId).get();
        dish.setImageUrl(url);
    }

    private String uploadPicture(MultipartFile pic) throws IOException {
        Map result = cloudinary.uploader().upload(pic.getBytes(), ObjectUtils.emptyMap());
        return String.valueOf(result.get("url"));
    }
}
