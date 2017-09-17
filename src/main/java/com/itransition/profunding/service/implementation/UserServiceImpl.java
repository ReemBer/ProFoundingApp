package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.UserDto;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.UserService;
import com.itransition.profunding.service.transformer.UserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 17.09.2017 17:39
 */
@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTransformer userTransformer;

    @Override
    public UserDto getUser(Long id) {
        return userTransformer.buildDto(userRepository.findOne(id));
    }
}
