package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.order.ReviewDto;
import by.bsuir.fooddelivio.dto.order.ReviewResponseDto;
import by.bsuir.fooddelivio.entity.Review;

public class ReviewMapper {
    public static Review reviewDtoToEntity(ReviewDto dto) {
        return Review.builder()
                .reviewMark(dto.getReviewMark())
                .reviewText(dto.getReviewText())
                .build();
    }

    public static ReviewResponseDto reviewEntityToDto(Review review) {
        return ReviewResponseDto.builder()
                .id(review.getId())
                .reviewText(review.getReviewText())
                .reviewDate(review.getReviewDate())
                .restaurantId(review.getRefersTo().getId())
                .userId(review.getAuthor().getId())
                .reviewMark(review.getReviewMark())
                .orderId(review.getOrder().getId())
                .build();
    }
}
