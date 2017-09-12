package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.db.UserRole;
import com.itransition.profunding.service.Transformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 11.09.2017 3:19
 */
@Service
@RequiredArgsConstructor
public class RegistrationUserTransformer implements Transformer<User, RegistrationData> {

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public RegistrationData makeDto(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public User makeEntity(RegistrationData registrationData) {
        User user = new User();
        user.setUsername(registrationData.getUsername());
        user.setPassword(passwordEncoder.encode(registrationData.getPassword()));
        user.setEmail(registrationData.getEmail());
        user.setUserRole(UserRole.ROLE_NO_PROOFED_USER);
        return user;
    }
}
