package by.bsuir.fooddelivio.mapper;

import by.bsuir.fooddelivio.dto.RegisterUserDto;
import by.bsuir.fooddelivio.dto.UserResponseDto;
import by.bsuir.fooddelivio.entity.User;

public class UserMapper {
    public static User registerUserDtoToEntity(RegisterUserDto dto) {
        return User.builder()
                .name(dto.getName())
                .password(dto.getPassword())
                .deliveryAddress(dto.getDeliveryAddress())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .build();
    }

    public static UserResponseDto userToResponseDto(User user) {
        return UserResponseDto.builder()
                .name(user.getName())
                .email(user.getEmail())
                .deliveryAddress(user.getDeliveryAddress())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
