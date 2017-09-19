package com.itransition.profunding.service.implementation;

import com.itransition.profunding.exception.repository.ProjectSavingException;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.ProfileDto;
import com.itransition.profunding.model.dto.UserDto;
import com.itransition.profunding.model.dto.UserImageOnlyDto;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.UserService;
import com.itransition.profunding.service.transformer.ProfileTransformer;
import com.itransition.profunding.service.transformer.UserTransformer;
import com.sun.org.apache.xpath.internal.operations.Bool;
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
    private final ProfileTransformer profileTransformer;

    @Override
    public ProfileDto getProfile(Long id) {
        return profileTransformer.buildDto(userRepository.findOne(id));
    }

    @Override
    public Boolean updateUser(UserImageOnlyDto userImageOnlyDto) {
        this.userRepository.updateUserById(userImageOnlyDto.getImage(), userImageOnlyDto.getId());
        User user = userRepository.findById(userImageOnlyDto.getId());
        if (user == null)
            return false;
        else if (user.getImage().equals(userImageOnlyDto.getImage()))
            return true;
        return false;
    }
}
