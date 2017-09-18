package com.itransition.profunding.security.service;

import com.itransition.profunding.exception.auth.UserNotFoundException;
import com.itransition.profunding.exception.registration.UnconfirmedUserException;
import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.repository.RegistrationDataRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.security.model.JwtUserDetails;
import com.itransition.profunding.service.dto.JsonException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 07.09.2017 14:24
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {


    private final UserRepository userRepository;
    private final RegistrationDataRepository registrationDataRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = this.userRepository.findUserByUsername(username);
        if (byUsername == null) {
            RegistrationData registrationData = registrationDataRepository.findByUsername(username);
            if (registrationData == null) {
                throw new UserNotFoundException("User not found.");
            } else {
                throw new UnconfirmedUserException();
            }
        } else {
            return new JwtUserDetails(byUsername);
        }
    }
}
