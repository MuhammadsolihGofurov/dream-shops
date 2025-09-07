package com.simpledev.dreamshops.data;

import com.simpledev.dreamshops.model.Role;
import com.simpledev.dreamshops.model.User;
import com.simpledev.dreamshops.repository.RoleRepository;
import com.simpledev.dreamshops.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ROLE_ADMIN", "ROLE_USER");
        createDefaultRoles(defaultRoles);
        createDefaultUsers();
        createDefaultAdmins();
    }

    private void createDefaultRoles(Set<String> roles) {
        roles.forEach(roleName -> {
            if (roleRepository.findByName(roleName).isEmpty()) {
                roleRepository.save(new Role(roleName));
                System.out.println("Role " + roleName + " created");
            }
        });
    }

    private void createDefaultUsers() {
        Optional<Role> userRoleOpt = roleRepository.findByName("ROLE_USER");
        if (userRoleOpt.isEmpty()) return; // agar yo‘q bo‘lsa chiqib ketadi

        Role userRole = userRoleOpt.get();
        for (int i = 1; i <= 5; i++) {
            String defaultEmail = "user" + i + "@gmail.com";
            if (userRepository.existsByEmail(defaultEmail)) continue;

            User user = new User();
            user.setFirstName("The user");
            user.setLastName("User" + i);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("secret"));
            user.setRoles(Set.of(userRole)); // faqat saqlangan role
            userRepository.save(user);

            System.out.println("Default User " + i + " created");
        }
    }

    private void createDefaultAdmins() {
        Optional<Role> adminRoleOpt = roleRepository.findByName("ROLE_ADMIN");
        if (adminRoleOpt.isEmpty()) return;

        Role adminRole = adminRoleOpt.get();
        for (int i = 1; i <= 2; i++) {
            String defaultEmail = "admin_email" + i + "@gmail.com";
            if (userRepository.existsByEmail(defaultEmail)) continue;

            User user = new User();
            user.setFirstName("Admin");
            user.setLastName("Admin" + i);
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("secret"));
            user.setRoles(Set.of(adminRole));
            userRepository.save(user);

            System.out.println("Default admin " + i + " created");
        }
    }
}
