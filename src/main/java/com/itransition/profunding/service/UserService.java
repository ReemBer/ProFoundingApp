package com.itransition.profunding.service;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.ProfileDto;
import com.itransition.profunding.model.dto.UserDto;
import com.itransition.profunding.model.dto.UserImageOnlyDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 17.09.2017 17:38
 */
public interface UserService {
    ProfileDto getProfile(Long id);
    Boolean updateUser(UserImageOnlyDto userImageOnlyDto);
}
