package by.bsuir.fooddelivio.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponseDto {
    String name;
    String email;
    String phoneNumber;
    String deliveryAddress;
}
