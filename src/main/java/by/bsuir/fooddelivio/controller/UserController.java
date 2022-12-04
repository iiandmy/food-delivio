package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.config.JwtTokenUtil;
import by.bsuir.fooddelivio.dto.order.OrderResponseDto;
import by.bsuir.fooddelivio.entity.User;
import by.bsuir.fooddelivio.mapper.OrderMapper;
import by.bsuir.fooddelivio.service.OrderService;
import by.bsuir.fooddelivio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final OrderService orderService;
    private final JwtTokenUtil jwtTokenUtil;

    @GetMapping("/authorized/get_orders")
    public ResponseEntity<List<OrderResponseDto>> getOrders(HttpServletRequest request) {
        User user = getUserFromToken(request);
        return ResponseEntity.ok(
                userService.getOrders(user).stream()
                        .map((order) -> OrderMapper.orderEntityToDto(order, orderService.getReview(order.getId())))
                        .collect(Collectors.toList())
        );
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
}
