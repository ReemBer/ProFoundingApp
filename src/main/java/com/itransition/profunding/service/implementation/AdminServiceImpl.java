package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.ConfirmRequestPk;
import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.db.UserRole;
import com.itransition.profunding.model.dto.user.AdminPageUserDto;
import com.itransition.profunding.repository.ConfirmRequestRepository;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.service.AdminService;
import com.itransition.profunding.service.transformer.AdminPageUserTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 24.09.2017 20:22
 */
@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ConfirmRequestRepository confirmRequestRepository;
    private final AdminPageUserTransformer adminPageUserTransformer;

    @Override
    public List<AdminPageUserDto> getUsers() {
        return adminPageUserTransformer
                .buildDtoList(userRepository
                        .findAllByRoleNotContainingOrderByIdDesc(UserRole.ROLE_ADMIN));
    }

    @Override
    @Transactional
    public boolean setBlockingStatus(List<AdminPageUserDto> users, boolean block) {
        for (AdminPageUserDto user: users) {
            userRepository.setAdminChanges(block, user.getId());
        }
        return true;
    }

    @Override
    public boolean setAsConfirmed(Long userId) {
        User user = userRepository.setAsConfirmed(UserRole.ROLE_PROOFED_USER, userId);
        if(user != null) {
            confirmRequestRepository.delete(new ConfirmRequestPk(user));
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUsers(List<AdminPageUserDto> users) {
        userRepository.delete(adminPageUserTransformer.parseDtoList(users));
        return true;
    }
}
