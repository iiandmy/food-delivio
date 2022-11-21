package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.dto.RegisterUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(RegisterUserDto dto) {
        return ResponseEntity.ok("ok");
    }
}
