package com.abletocode.adsdentalsystem.config;

import com.abletocode.adsdentalsystem.domain.Role;
import com.abletocode.adsdentalsystem.domain.enums.UserRole;
import com.abletocode.adsdentalsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {
        for (UserRole userRole : UserRole.values()) {
            // Check if the role already exists
            boolean exists = roleRepository.findByName(userRole.name()).isPresent();
            if (!exists) {
                Role role = new Role();
                role.setName(userRole.name()); // Convert ENUM to string
                roleRepository.save(role);
            }
        }
    }
}
