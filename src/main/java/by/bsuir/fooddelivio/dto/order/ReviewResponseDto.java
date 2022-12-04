package by.bsuir.fooddelivio.dto.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewResponseDto {
    private Long id;
    private Long userId;
    private Long orderId;
    private Long restaurantId;
    private String reviewText;
    private double reviewMark;
    private String reviewDate;
}
