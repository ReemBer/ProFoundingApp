package com.itransition.profunding.service;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.UserDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 17.09.2017 17:38
 */
public interface UserService {
    UserDto getUser(Long id);
}
