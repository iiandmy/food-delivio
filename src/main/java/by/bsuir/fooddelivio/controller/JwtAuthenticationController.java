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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    @PostMapping("/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest request) throws Exception {
        authenticate(request.getEmail(), request.getPassword());
        UserDetails user = userService.loadUserByUsername(request.getEmail());

        String accessToken = jwtTokenUtil.generateAccessToken(user);
        String refreshToken = jwtTokenUtil.generateRefreshToken(user);
        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
    }

    @PostMapping("/authorized/refresh")
    public ResponseEntity<JwtResponse> refreshTokens(HttpServletRequest request) throws Exception {
        User user = getUserFromToken(request);
        authenticate(user.getEmail(), user.getPassword());

        UserDetails userDetails = userService.loadUserByUsername(user.getEmail());

        String accessToken = jwtTokenUtil.generateAccessToken(userDetails);
        String refreshToken = jwtTokenUtil.generateRefreshToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(accessToken, refreshToken));
    }

    @GetMapping("/authorized/is_admin")
    public ResponseEntity<Boolean> isAdmin(HttpServletRequest request) {
        User user = getUserFromToken(request);
        return ResponseEntity.ok(user.getRoles().stream().anyMatch((role) -> role.getName().equals("ROLE_ADMIN")));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDto> registerUser(@RequestBody RegisterUserDto dto) throws UserAlreadyExistsException {
        userService.createUser(dto);
        return ResponseEntity.ok(
                UserMapper.userToResponseDto(userService.findUserByEmail(dto.getEmail()).get())
        );
    }

    @GetMapping("/authorized/get_user")
    public ResponseEntity<UserResponseDto> getUser(HttpServletRequest request) {
        return ResponseEntity.ok(
                UserMapper.userToResponseDto(getUserFromToken(request))
        );
    }

    @GetMapping("/authorized/is_token_expired")
    public ResponseEntity<Boolean> isTokenExpired(HttpServletRequest request) {
        return ResponseEntity.ok(
                checkIfTokenExpired(request)
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

    private User getUserFromToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String token = authorizationHeader.substring(7);
            String email = jwtTokenUtil.getUserNameFromToken(token);
            return userService.findUserByEmail(email).get();
        }
        throw new RuntimeException();
    }

    private Boolean checkIfTokenExpired(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            String token = authorizationHeader.substring(7);
            return jwtTokenUtil.isTokenExpired(token);
        }
        throw new RuntimeException();
    }

}
