package com.tchokoapps.springboot.ecommerce.training.service;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import com.tchokoapps.springboot.ecommerce.training.repository.RoleRepository;
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

    public void save(@NonNull Role role) {
        Role savedRole = roleRepository.save(role);
        log.info("Role: {} created successfully", savedRole);
    }

    public void save(@NonNull List<Role> roles) {
        roles.forEach(this::save);
    }

    public List<Role> retrieveAllRoles() {
        return (List<Role>) roleRepository.findAll();
    }

}
