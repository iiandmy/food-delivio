package by.bsuir.fooddelivio.dto;

import lombok.Data;

@Data
public class RegisterUserDto {
    String name;
    String password;
    String email;
    String phoneNumber;
    String deliveryAddress;
}
