package com.abletocode.adsdentalsystem.repository;

import com.abletocode.adsdentalsystem.domain.Role;
import com.abletocode.adsdentalsystem.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
