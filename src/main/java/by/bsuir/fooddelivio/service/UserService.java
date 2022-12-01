package by.bsuir.fooddelivio.service;

import by.bsuir.fooddelivio.dto.LoginUserDto;
import by.bsuir.fooddelivio.dto.RegisterUserDto;
import by.bsuir.fooddelivio.entity.User;
import by.bsuir.fooddelivio.exception.UserAlreadyExistsException;
import by.bsuir.fooddelivio.exception.UserDoesntExistsException;
import by.bsuir.fooddelivio.exception.UserWrongPasswordException;
import by.bsuir.fooddelivio.mapper.UserMapper;
import by.bsuir.fooddelivio.repository.RoleRepository;
import by.bsuir.fooddelivio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    public Optional<User> findUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> findUserByPhone(String phone) {
        return userRepository.findUserByPhoneNumber(phone);
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public User createUser(RegisterUserDto dto) throws UserAlreadyExistsException {
        if (
                findUserByEmail(dto.getEmail()).isPresent() ||
                findUserByPhone(dto.getPhoneNumber()).isPresent()
        ) {
            throw new UserAlreadyExistsException();
        }
        User createdUser = UserMapper.registerUserDtoToEntity(dto);
        createdUser.setRoles(List.of(roleRepository.findRoleByName("ROLE_USER").get()));
        userRepository.save(createdUser);
        return createdUser;
    }

    public User loginUser(LoginUserDto dto) throws UserDoesntExistsException, UserWrongPasswordException {
        Optional<User> user = findUserByEmail(dto.getLogin());
        user = user.isPresent() ? user : findUserByPhone(dto.getLogin());
        if (user.isEmpty()) {
            throw new UserDoesntExistsException();
        }
        if (user.get().getPassword().equals(dto.getPassword())) {
            return user.get();
        }
        throw new UserWrongPasswordException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userFromDb = userRepository.findUserByEmail(username);

        if (userFromDb.isEmpty()) {
            throw new UsernameNotFoundException("User with such email - " + username + " is not founded");
        }

        return userFromDb.get();
    }
}
