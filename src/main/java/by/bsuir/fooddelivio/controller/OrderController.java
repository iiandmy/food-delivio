package by.bsuir.fooddelivio.controller;

import by.bsuir.fooddelivio.config.JwtTokenUtil;
import by.bsuir.fooddelivio.dto.order.OrderDto;
import by.bsuir.fooddelivio.dto.order.OrderResponseDto;
import by.bsuir.fooddelivio.dto.order.ReviewDto;
import by.bsuir.fooddelivio.dto.order.ReviewResponseDto;
import by.bsuir.fooddelivio.entity.Review;
import by.bsuir.fooddelivio.entity.User;
import by.bsuir.fooddelivio.mapper.OrderMapper;
import by.bsuir.fooddelivio.mapper.ReviewMapper;
import by.bsuir.fooddelivio.service.OrderService;
import by.bsuir.fooddelivio.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/authorized/make_an_order")
    public ResponseEntity<OrderResponseDto> createOrder(
            @RequestBody OrderDto orderDto,
            HttpServletRequest request
    ) {
        User user = getUserFromToken(request);
        return ResponseEntity.ok(
                OrderMapper.orderEntityToDto(orderService.createOrder(orderDto, user), null)
        );
    }

    @PostMapping("/authorized/add_review")
    public ResponseEntity<OrderResponseDto> createReview(
            @RequestBody ReviewDto reviewDto,
            HttpServletRequest request
    ) {
        User user = getUserFromToken(request);
        Review review = orderService.addReviewToOrder(
                user,
                ReviewMapper.reviewDtoToEntity(reviewDto),
                orderService.getOrderById(reviewDto.getOrderId()),
                reviewDto.getRestaurantId()
        );
        return ResponseEntity.ok(
                OrderMapper.orderEntityToDto(orderService.getOrderById(review.getOrder().getId()), review)
        );
    }

    @GetMapping("/authorized/get_order/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(
                OrderMapper.orderEntityToDto(orderService.getOrderById(orderId), orderService.getReview(orderId))
        );
    }

    @GetMapping("/authorized/get_review/{orderId}")
    public ResponseEntity<ReviewResponseDto> getReview(@PathVariable("orderId") Long orderId) {
        return ResponseEntity.ok(
            ReviewMapper.reviewEntityToDto(orderService.getReview(orderId))
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
