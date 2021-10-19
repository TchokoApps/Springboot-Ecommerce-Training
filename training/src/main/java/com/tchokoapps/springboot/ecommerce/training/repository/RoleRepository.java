package com.tchokoapps.springboot.ecommerce.training.repository;

import com.tchokoapps.springboot.ecommerce.training.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
