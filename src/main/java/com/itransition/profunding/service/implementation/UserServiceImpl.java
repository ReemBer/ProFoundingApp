package com.itransition.profunding.service.implementation;

import com.itransition.profunding.model.db.User;
import com.itransition.profunding.model.dto.ProfileDto;
import com.itransition.profunding.model.dto.UserImageOnlyDto;
import com.itransition.profunding.repository.UserRepository;
import com.itransition.profunding.security.SecurityHelper;
import com.itransition.profunding.security.model.JwtUserDetails;
import com.itransition.profunding.service.UserService;
import com.itransition.profunding.service.transformer.ProfileTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
        checkAccess(userImageOnlyDto);
        this.userRepository.updateUserById(userImageOnlyDto.getImage(), userImageOnlyDto.getId());
        User user = userRepository.findById(userImageOnlyDto.getId());
        if (user == null)
            return false;
        else if (user.getImage().equals(userImageOnlyDto.getImage()))
            return true;
        return false;
    }

    private void checkAccess(UserImageOnlyDto userImageOnlyDto) {
        JwtUserDetails userDetails = (JwtUserDetails) SecurityHelper.getAuthenticationWithCheck().getPrincipal();
        boolean accessed = userDetails.getId().equals(userImageOnlyDto.getId())
                || userDetails.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
        if (!accessed) {
            throw new AccessDeniedException("You have not authorities for this operation.");
        }
    }
}
