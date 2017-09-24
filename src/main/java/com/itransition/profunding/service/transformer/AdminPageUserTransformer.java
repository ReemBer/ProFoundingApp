package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.ConfirmRequest;
import com.itransition.profunding.model.db.ConfirmRequestPk;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.user.AdminPageUserDto;
import com.itransition.profunding.repository.ConfirmRequestRepository;
import com.itransition.profunding.service.TransformerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 19:10
 */
@Service
@RequiredArgsConstructor
public class AdminPageUserTransformer extends TransformerService<User, AdminPageUserDto> {

    private final ConfirmRequestRepository confirmRequestRepository;

    @Override
    public User parseDto(AdminPageUserDto dto) {
        return modelMapper.map(dto, User.class);
    }

    @Override
    public AdminPageUserDto buildDto(User entity) {
        AdminPageUserDto dto = modelMapper.map(entity, AdminPageUserDto.class);
        ConfirmRequest confirmRequest = confirmRequestRepository.findOne(new ConfirmRequestPk(entity));
        if (confirmRequest != null) {
            dto.setScanOfPassport(confirmRequest.getScanOfPassport());
        }
        return dto;
    }
}
