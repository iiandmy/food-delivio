package by.bsuir.fooddelivio.dto.order;

import lombok.Data;

@Data
public class ReviewDto {
    private String reviewText;
    private double reviewMark;
    private Long orderId;
    private Long restaurantId;
}
