package com.tchokoapps.springboot.ecommerce.training.services;

import com.tchokoapps.springboot.ecommerce.training.entities.Role;
import com.tchokoapps.springboot.ecommerce.training.repositories.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class RoleService {

    private RoleRepository roleRepository;

    public void createRole(@NonNull Role role) {
        Role savedRole = roleRepository.save(role);
        log.info("Role: {} created successfully", savedRole);
    }

    public void createRoles(@NonNull List<Role> roles) {
        roles.forEach(this::createRole);
    }
}
