package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.auth.AuthUserDto;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 08.09.2017 16:29
 */
@Service
@RequiredArgsConstructor
public class AuthUserTransformer extends TransformerService<User, AuthUserDto> {

    @Override
    public User parseDto(AuthUserDto dto) {
        return null;
    }

    @Override
    public AuthUserDto buildDto(User entity) {
        return modelMapper.map(entity, AuthUserDto.class);
    }
}
