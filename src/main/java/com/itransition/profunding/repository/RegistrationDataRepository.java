package com.itransition.profunding.repository;

import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.dto.RegistrationRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 21:35
 */
@Repository
public interface RegistrationDataRepository extends JpaRepository<RegistrationData, Long> {

    void deleteByExpirationTimeLessThan(Long currentTime);

    RegistrationData findByEmail(String email);
    RegistrationData findByUsername(String username);
    RegistrationData findByRegistrationHash(String registrationHash);
}
