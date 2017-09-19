package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.ProfileDto;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileTransformer extends TransformerService<User, ProfileDto> {

    @Override
    public User parseDto(ProfileDto profileDto) {
        return modelMapper.map(profileDto, User.class);
    }

    @Override
    public ProfileDto buildDto(User entity) {
        return modelMapper.map(entity, ProfileDto.class);
    }
}
