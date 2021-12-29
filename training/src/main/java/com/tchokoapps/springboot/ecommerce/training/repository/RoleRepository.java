package com.tchokoapps.springboot.ecommerce.training.repository;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
