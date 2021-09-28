package com.tchokoapps.springboot.ecommerce.training.repositories;

import com.tchokoapps.springboot.ecommerce.training.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Integer> {
}
