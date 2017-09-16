package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.AuthUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:05
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Query("select new com.itransition.profunding.model.dto.AuthUserDto(u.id, u.username, u.role) " +
           "from User u where u.id = :idParam")
    AuthUserDto getAuthUserById(@Param("idParam")Long id);

    @Query("select new com.itransition.profunding.model.dto.AuthUserDto(u.id, u.username, u.role) " +
           "from User u where u.username = :usernameParam")
    AuthUserDto getAuthUserByUsername(@Param("usernameParam")String username);
}
