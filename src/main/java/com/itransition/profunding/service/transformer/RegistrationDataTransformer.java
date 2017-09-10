package com.itransition.profunding.service.transformer;

import com.itransition.profunding.model.db.RegistrationData;
import com.itransition.profunding.model.dto.RegistrationRequestDto;
import com.itransition.profunding.service.Transformer;
import com.itransition.profunding.util.TimeConverter;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.crypto.macs.HMac;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * @author v.tarasevich
 * @version 1.0
 * @since 11.09.2017 0:10
 */
@Service
@RequiredArgsConstructor
public class RegistrationDataTransformer implements Transformer<RegistrationData, RegistrationRequestDto> {

    private static final String PROP_CONFIRMATION_EXPIRATION_TIME = "registration.confirmation.expiration_time";

    private final Environment environment;

    @Override
    public RegistrationRequestDto makeDto(RegistrationData registrationData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public RegistrationData makeEntity(RegistrationRequestDto registrationRequestDto) {
        RegistrationData registrationData = new RegistrationData();
        fillTheFields(registrationData, registrationRequestDto);
        return registrationData;
    }

    private void fillTheFields(RegistrationData registrationData, RegistrationRequestDto registrationRequestDto) {
        long expTime = getExpTimeInMills();
        registrationData.setExpirationTime(expTime);
        registrationData.setUsername(registrationRequestDto.getUsername());
        registrationData.setEmail(registrationRequestDto.getEmail());
        registrationData.setPassword(registrationRequestDto.getPassword());
        registrationData.setRegistrationHash(createHash(registrationData.getUsername()));
    }

    private long getExpTimeInMills() {
        String expTimeStr = environment.getProperty(PROP_CONFIRMATION_EXPIRATION_TIME);
        long countOfHours = Long.parseLong(expTimeStr);
        return System.currentTimeMillis() + TimeConverter.HoursToMills(countOfHours);
    }

    private String createHash(String username) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(11, new SecureRandom());
        return bCryptPasswordEncoder.encode(username);
    }
}
