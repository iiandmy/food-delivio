package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.dto.LoginUserDto;
import by.bsuir.fooddelivio.dto.RegisterUserDto;
import by.bsuir.fooddelivio.entity.User;
import by.bsuir.fooddelivio.exception.UserAlreadyExistsException;
import by.bsuir.fooddelivio.exception.UserDoesntExistsException;
import by.bsuir.fooddelivio.exception.UserWrongPasswordException;
import by.bsuir.fooddelivio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


}
