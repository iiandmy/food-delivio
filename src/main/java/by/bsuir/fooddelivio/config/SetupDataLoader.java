package by.bsuir.fooddelivio.config;

import by.bsuir.fooddelivio.entity.Role;
import by.bsuir.fooddelivio.entity.User;
import by.bsuir.fooddelivio.repository.RoleRepository;
import by.bsuir.fooddelivio.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        createRoleIfNotFound("ROLE_ADMIN");
        createRoleIfNotFound("ROLE_USER");

        Role adminRole = roleRepository.findRoleByName("ROLE_ADMIN").get();
        Role userRole = roleRepository.findRoleByName("ROLE_USER").get();
        User user = new User();
        user.setName("Admin");
        user.setEmail("admin@fooddelivio.by");
        user.setPassword("123123admin");
        user.setRoles(Arrays.asList(userRole, adminRole));
        userRepository.save(user);

        alreadySetup = true;
    }

    @Transactional
    Role createRoleIfNotFound(String name) {
        Optional<Role> unwrappedRole = roleRepository.findRoleByName(name);
        Role role = unwrappedRole.orElseGet(() -> createRole(name));
        roleRepository.save(role);
        return role;
    }

    private Role createRole(String name) {
        Role role = new Role();
        role.setName(name);
        return role;
    }
}
