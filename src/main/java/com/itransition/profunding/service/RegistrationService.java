package com.itransition.profunding.service;

import com.itransition.profunding.model.dto.registration.RegistrationRequestDto;
import com.itransition.profunding.model.dto.registration.RegistrationResponseDto;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 10.09.2017 16:26
 */
public interface RegistrationService {

    RegistrationResponseDto register(RegistrationRequestDto registrationData);
    void confirm(String confirmationHash);
    void clearExpiredRegistrationData();
}
