package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.config.JwtTokenUtil;
import by.bsuir.fooddelivio.dto.RegisterUserDto;
import by.bsuir.fooddelivio.dto.UserResponseDto;
import by.bsuir.fooddelivio.dto.jwt.JwtRequest;
import by.bsuir.fooddelivio.dto.jwt.JwtResponse;
import by.bsuir.fooddelivio.entity.User;
import by.bsuir.fooddelivio.exception.UserAlreadyExistsException;
import by.bsuir.fooddelivio.mapper.UserMapper;
import by.bsuir.fooddelivio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        authenticate(request.getEmail(), request.getPassword());

        String token = jwtTokenUtil.generateToken(
            userService.loadUserByUsername(request.getEmail())
        );
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody RegisterUserDto dto) throws UserAlreadyExistsException {
        userService.createUser(dto);
        return ResponseEntity.ok(
                UserMapper.userToResponseDto(userService.findUserByEmail(dto.getEmail()).get())
        );
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException exception) {
            throw new Exception("USER_DISABLED", exception);
        } catch (BadCredentialsException exception) {
            throw new Exception("INVALID_CREDENTIALS", exception);
        }
    }

}
