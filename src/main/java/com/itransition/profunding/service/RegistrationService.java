package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.ConfirmRegistrationResponseStatus;
import com.itransition.profunding.model.dto.RegistrationRequestDto;
import com.itransition.profunding.model.dto.RegistrationResponseDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 16:26
 */
public interface RegistrationService {

    RegistrationResponseDto register(RegistrationRequestDto registrationData);
    ConfirmRegistrationResponseStatus confirm(String confirmationHash);
    void clearExpiredRegistrationData();
}
