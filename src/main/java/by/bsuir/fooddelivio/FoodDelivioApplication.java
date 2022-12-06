package by.bsuir.fooddelivio;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FoodDelivioApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoodDelivioApplication.class, args);
    }

    @Bean
    public Cloudinary setupCloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dytws5eom",
                "api_key", "643751387367933",
                "api_secret", "ikgOkBoShyqI9P5hjZ7F54zABUA"
        ));
    }

    @Bean
    public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
}
