package by.bsuir.fooddelivio.service;

import by.bsuir.fooddelivio.dto.RegisterUserDto;
import by.bsuir.fooddelivio.mapper.UserMapper;
import by.bsuir.fooddelivio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {
    private final UserRepository userRepository;

    // FIXME
    public void createUser(RegisterUserDto dto) {
        userRepository.save(UserMapper.registerUserDtoToEntity(dto));
    }
}
