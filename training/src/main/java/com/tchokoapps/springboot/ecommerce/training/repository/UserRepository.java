package com.tchokoapps.springboot.ecommerce.training.repository;

import com.tchokoapps.springboot.ecommerce.training.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    Long countById(Integer id);
}
