package com.itransition.profunding.security.service;

import com.itransition.profunding.model.db.User;
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

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User byUsername = this.userRepository.findUserByUsername(username);
        return Optional.ofNullable(byUsername).map(JwtUserDetails::new)
                       .orElseThrow(() -> new JsonException("User not found."));
    }
}
