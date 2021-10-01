package com.tchokoapps.springboot.ecommerce.training.repositories;

import com.tchokoapps.springboot.ecommerce.training.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

    public User findByEmail(String email);
}
