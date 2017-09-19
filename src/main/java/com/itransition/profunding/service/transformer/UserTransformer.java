package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.UserDto;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 16.09.2017 23:20
 */
@Service
@RequiredArgsConstructor
public class UserTransformer extends TransformerService<User, UserDto> {

    @Override
    public User parseDto(UserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public UserDto buildDto(User entity) {
        UserDto userDto = modelMapper.map(entity, UserDto.class);
        userDto.setPassword("");
        return userDto;
    }
}
