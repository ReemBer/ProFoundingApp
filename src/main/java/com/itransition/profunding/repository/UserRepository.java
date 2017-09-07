package com.itransition.profunding.repository;

import com.itransition.profunding.model.DB.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:05
 */
public interface UserRepository extends JpaRepository<User, Long>{
    User findUserByUsername(String username);
}
