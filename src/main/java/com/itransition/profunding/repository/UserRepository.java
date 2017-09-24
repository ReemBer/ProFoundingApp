package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.db.UserRole;
import com.itransition.profunding.model.dto.auth.AuthUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 6:05
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @Query("select new com.itransition.profunding.model.dto.auth.AuthUserDto(u.id, u.username, u.role, u.image) " +
           "from User u where u.id = :idParam")
    AuthUserDto getAuthUserById(@Param("idParam")Long id);

    @Modifying
    @Query("update User u set u.image =:newImage where u.id =:idParam")
    void updateUserById(@Param("newImage") String image, @Param("idParam") Long id);

    User findById(Long id);

    @Query("select u from User u where not u.role = ?1")
    List<User> findAllByRoleNotContainingOrderByIdDesc(UserRole role);

    @Modifying
    @Query("update User u set u.isBlocked = ?1 where u.id = ?2")
    void setAdminChanges(Boolean isBlocked, Long id);

    @Query("update User u set u.role = ?1 where u.id = ?2")
    User setAsConfirmed(UserRole role, Long id);

    int deleteById(Long id);
}
