package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.db.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 11.09.2017 3:19
 */
@Component
@RequiredArgsConstructor
public class RegistrationUserTransformer {

    private final BCryptPasswordEncoder passwordEncoder;


    public RegistrationData makeDto(User user) {
        throw new UnsupportedOperationException();
    }

    public User makeEntity(RegistrationData registrationData) {
        User user = new User();
        user.setUsername(registrationData.getUsername());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setEmail(registrationData.getEmail());
        user.setUserRole(UserRole.ROLE_NO_PROOFED_USER);
        return user;
    }
}
